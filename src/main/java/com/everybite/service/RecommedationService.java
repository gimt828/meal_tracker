package com.everybite.service;

import com.everybite.dto.RecommendedMealDto;
import com.everybite.entity.Food;
import com.everybite.entity.UserGoal;
import com.everybite.entity.DietType;
import com.everybite.repository.FoodRepository;
import com.everybite.repository.MealIntakeRecordRepository;
import com.everybite.repository.UserGoalRepository;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final FoodRepository foodRepository;
    private final MealIntakeRecordRepository mealIntakeRecordRepository;
    private final UserGoalRepository userGoalRepository;

    public RecommendationService(FoodRepository foodRepository,
                                 MealIntakeRecordRepository mealIntakeRecordRepository,
                                 UserGoalRepository userGoalRepository) {
        this.foodRepository = foodRepository;
        this.mealIntakeRecordRepository = mealIntakeRecordRepository;
        this.userGoalRepository = userGoalRepository;
    }

    /**
     * 메인 추천 함수
     * @param mealType (아침, 점심, 저녁, 간식 등)
     */
    public List<RecommendedMealDto> recommendMeals(String mealType) {

        // ️1. 사용자 목표 정보
        UserGoal userGoal = userGoalRepository.findByUserId(1L); // 단일 사용자 구조
        if (userGoal == null) throw new IllegalStateException("UserGoal not found.");

        double targetCalories = userGoal.getTarget_calories();
        DietType dietType = userGoal.getDietType();

        // 2. 목표 비율 자동 반영 (사용자 지정값이 0이면 Enum 기본값 사용)
        double targetCarbRatio = userGoal.getTarget_carb_ratio() > 0 
                ? userGoal.getTarget_carb_ratio()
                : dietType.getCarbRatio();
        double targetProteinRatio = userGoal.getTarget_protein_ratio() > 0 
                ? userGoal.getTarget_protein_ratio()
                : dietType.getProteinRatio();
        double targetFatRatio = userGoal.getTarget_fat_ratio() > 0 
                ? userGoal.getTarget_fat_ratio()
                : dietType.getFatRatio();

        // 3. 오늘 섭취량 조회
        LocalDate today = LocalDate.now();
        double currentCalories = mealIntakeRecordRepository.sumCaloriesForToday(today);
        double currentCarb = mealIntakeRecordRepository.sumCarbForToday(today);
        double currentProtein = mealIntakeRecordRepository.sumProteinForToday(today);
        double currentFat = mealIntakeRecordRepository.sumFatForToday(today);

        // 4. 남은 칼로리 및 탄단지(g) 계산
        double remainingCalories = Math.max(0, targetCalories - currentCalories);
        double remainingCarb = Math.max(0, (targetCalories * (targetCarbRatio / 100.0) / 4) - currentCarb);
        double remainingProtein = Math.max(0, (targetCalories * (targetProteinRatio / 100.0) / 4) - currentProtein);
        double remainingFat = Math.max(0, (targetCalories * (targetFatRatio / 100.0) / 9) - currentFat);

        // 5. 최근 3일간 먹은 음식 제외
        LocalDate threeDaysAgo = today.minusDays(3);
        List<String> recentFoods = mealIntakeRecordRepository.findRecentFoodNames(threeDaysAgo);

        // 6. 음식 후보 필터링 (식사타입 + 중복제외 + 칼로리제한)
        List<Food> candidates = foodRepository.findAll().stream()
                .filter(f -> !recentFoods.contains(f.getFoodName()))
                .filter(f -> f.getMealType().equalsIgnoreCase(mealType))
                .filter(f -> f.getCalories() <= remainingCalories / 2) // 1회 식사 기준 제한
                .collect(Collectors.toList());

        // 7. Step 1: 규칙 기반 필터링
        List<Food> filtered = candidates.stream()
                .filter(f -> matchMacroBalance(f, remainingCarb, remainingProtein, remainingFat))
                .collect(Collectors.toList());

        // 8. Step 2: 점수 기반 정렬
        List<RecommendedMealDto> scored = filtered.stream()
                .map(f -> new RecommendedMealDto(f, calculateScore(f, remainingCarb, remainingProtein, remainingFat)))
                .sorted(Comparator.comparingDouble(RecommendedMealDto::getScore).reversed())
                .limit(5)
                .collect(Collectors.toList());

        return scored;
    }

    /**
     * 규칙 기반: 영양소 비율이 남은 비율과 너무 다르지 않은지 판단
     */
    private boolean matchMacroBalance(Food f, double remainC, double remainP, double remainF) {
        if (remainC == 0 || remainP == 0 || remainF == 0) return false;

        double carbRatio = f.getCarbohydrates() / remainC;
        double proteinRatio = f.getProtein() / remainP;
        double fatRatio = f.getFat() / remainF;

        double ratioDiff = Math.abs((carbRatio + proteinRatio + fatRatio) - 3);
        return ratioDiff < 1.5;
    }

    /**
     * 점수 기반: 영양소 적합도 + 칼로리 적정도 계산
     */
    private double calculateScore(Food f, double remainC, double remainP, double remainF) {
        // 1. 음식의 실제 비율 (%)
        double totalMacro = f.getCarbohydrates() + f.getProtein() + f.getFat();
        if (totalMacro == 0) return 0;

        double foodCarbRatio = f.getCarbohydrates() / totalMacro;
        double foodProteinRatio = f.getProtein() / totalMacro;
        double foodFatRatio = f.getFat() / totalMacro;

        // 2. 남은 목표 비율 (%)
        double remainTotal = remainC + remainP + remainF;
        double targetCarbRatio = remainC / remainTotal;
        double targetProteinRatio = remainP / remainTotal;
        double targetFatRatio = remainF / remainTotal;

        // 3. MacroRatioScore: 음식의 비율과 목표 비율 차이 기반
        double diffC = Math.abs(foodCarbRatio - targetCarbRatio);
        double diffP = Math.abs(foodProteinRatio - targetProteinRatio);
        double diffF = Math.abs(foodFatRatio - targetFatRatio);
        double macroRatioScore = 1 - ((diffC + diffP + diffF) / 3.0); // 평균 오차 기반

        // 4. CalorieScore: 남은 칼로리의 1/3 수준이 이상적이라고 가정
        double idealCal = (remainC * 4) + (remainP * 4) + (remainF * 9);
        double calorieScore = 1 - Math.min(Math.abs(f.getCalories() - idealCal / 3) / (idealCal / 3), 1);

        // 5. MacroBalancePenalty: 한 영양소가 60% 이상이면 감점
        double maxRatio = Math.max(foodCarbRatio, Math.max(foodProteinRatio, foodFatRatio));
        double penalty = (maxRatio > 0.6) ? (maxRatio - 0.6) * 2 : 0;
        double macroBalancePenalty = Math.max(0, 1 - penalty);

        // 6. 최종 점수 (가중 평균)
        double finalScore = (macroRatioScore * 0.5) + (calorieScore * 0.3) + (macroBalancePenalty * 0.2);
        return Math.max(0, Math.min(finalScore, 1)); // 0~1 사이로 보정
    }
 // 최근 추천 캐시 (식사타입별)
    private final Map<String, Set<String>> recentRecommendations = new ConcurrentHashMap<>();

    public List<RecommendedMealDto> recommendMealsWithReroll(String mealType) {
        // 간식은 추천하지 않음
        if (mealType.equalsIgnoreCase("간식")) {
            return Collections.emptyList();
        }

        // 1. 전체 추천 목록 생성
        List<RecommendedMealDto> allRecommendations = recommendMeals(mealType);

        // 2. 최근 추천 캐시 불러오기
        Set<String> cachedFoods = recentRecommendations.getOrDefault(mealType, new HashSet<>());

        // 3. 이전 추천과 겹치지 않는 음식만 필터링
        List<RecommendedMealDto> newRecommendations = allRecommendations.stream()
                .filter(r -> !cachedFoods.contains(r.getFoodName()))
                .collect(Collectors.toList());

        // 4. 만약 전부 겹친다면 캐시 초기화 후 전체 추천 재사용
        if (newRecommendations.isEmpty()) {
            cachedFoods.clear();
            newRecommendations = allRecommendations;
        }

        // 5. 캐시 갱신
        Set<String> updated = new HashSet<>(cachedFoods);
        newRecommendations.forEach(r -> updated.add(r.getFoodName()));
        recentRecommendations.put(mealType, updated);

        // 6. 최종 결과 반환
        return newRecommendations;
    }


}
