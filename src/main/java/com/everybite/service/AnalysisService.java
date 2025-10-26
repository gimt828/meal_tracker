package com.everybite.service;

import com.everybite.dto.MacroRatioDto;
import com.everybite.dto.MealCaloriesDto;
import com.everybite.dto.ChartDataDto;
import com.everybite.entity.MealIntakeRecord;
import com.everybite.entity.DietType;
import com.everybite.repository.MealIntakeRecordRepository;
import com.everybite.dto.NutrientBarDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AnalysisService {

    private final MealIntakeRecordRepository mealIntakeRecordRepository;

    public AnalysisService(MealIntakeRecordRepository mealIntakeRecordRepository) {
        this.mealIntakeRecordRepository = mealIntakeRecordRepository;
    }

    // ① 탄단지 비율 비교 (이미 있는 부분)
    public MacroRatioDto getDailyMacroRatio(LocalDate date, DietType dietType,
                                            Map<String, Double> customRatio) {

        Map<String, Double> recommended = (customRatio != null && !customRatio.isEmpty())
                ? customRatio
                : getDefaultRatio(dietType);

        List<Object[]> result = mealIntakeRecordRepository.findDailyMacroSums(date);
        double carbs = 0, protein = 0, fat = 0;
        if (!result.isEmpty() && result.get(0)[0] != null) {
            carbs = ((Number) result.get(0)[0]).doubleValue();
            protein = ((Number) result.get(0)[1]).doubleValue();
            fat = ((Number) result.get(0)[2]).doubleValue();
        }

        double total = carbs + protein + fat;
        Map<String, Double> actual = new LinkedHashMap<>();
        if (total > 0) {
            actual.put("탄수화물", Math.round(carbs / total * 1000) / 10.0);
            actual.put("단백질", Math.round(protein / total * 1000) / 10.0);
            actual.put("지방", Math.round(fat / total * 1000) / 10.0);
        } else {
            actual.put("탄수화물", 0.0);
            actual.put("단백질", 0.0);
            actual.put("지방", 0.0);
        }

        return new MacroRatioDto(recommended, actual);
    }

    // ② 하루 칼로리 시각화 (새로 추가하는 부분)
    public MealCaloriesDto getDailyMealCalories(LocalDate date, double goalCalories) {
        List<MealIntakeRecord> records = mealIntakeRecordRepository.findByDate(date);

        double breakfast = 0, lunch = 0, dinner = 0, snack = 0;

        for (MealIntakeRecord record : records) {
            switch (record.getMeal_type()) {
                case BREAKFAST -> breakfast += record.getCalories();
                case LUNCH -> lunch += record.getCalories();
                case DINNER -> dinner += record.getCalories();
                case SNACK -> snack += record.getCalories();
            }
        }

        double totalConsumed = breakfast + lunch + dinner + snack;
        double remaining = Math.max(goalCalories - totalConsumed, 0);

        List<ChartDataDto> meals = new ArrayList<>();
        meals.add(new ChartDataDto("아침", breakfast));
        meals.add(new ChartDataDto("점심", lunch));
        meals.add(new ChartDataDto("저녁", dinner));
        meals.add(new ChartDataDto("간식", snack));
        meals.add(new ChartDataDto("남은 칼로리", remaining));

        return new MealCaloriesDto(goalCalories, meals);
    }

    // 기본 비율 설정 (기존 메서드 유지)
    private Map<String, Double> getDefaultRatio(DietType dietType) {
        Map<String, Double> ratio = new LinkedHashMap<>();
        switch (dietType) {
            case BULK_UP -> {
                ratio.put("탄수화물", 55.0);
                ratio.put("단백질", 25.0);
                ratio.put("지방", 20.0);
            }
            case DIET -> {
                ratio.put("탄수화물", 40.0);
                ratio.put("단백질", 40.0);
                ratio.put("지방", 20.0);
            }
            case MAINTAIN -> {
                ratio.put("탄수화물", 50.0);
                ratio.put("단백질", 30.0);
                ratio.put("지방", 20.0);
            }
        }
        return ratio;
    }
 // ✅ ③ 탄단지별 kcal 막대 그래프
    public List<NutrientBarDto> getDailyNutrientBars(LocalDate date, double goalCalories, DietType dietType) {
        // 1️⃣ 식단 타입에 따른 기본 비율 불러오기
        Map<String, Double> ratio = getDefaultRatio(dietType);

        // 2️⃣ DB에서 해당 날짜의 총 탄단지(g) 조회
        List<Object[]> result = mealIntakeRecordRepository.findDailyMacroSums(date);
        double carbs = 0, protein = 0, fat = 0;
        if (!result.isEmpty() && result.get(0)[0] != null) {
            carbs = ((Number) result.get(0)[0]).doubleValue();
            protein = ((Number) result.get(0)[1]).doubleValue();
            fat = ((Number) result.get(0)[2]).doubleValue();
        }

        // 3️⃣ 실제 섭취 kcal 계산
        double carbKcal = carbs * 4;
        double proteinKcal = protein * 4;
        double fatKcal = fat * 9;

        // 4️⃣ 목표 kcal 계산 (비율 기반)
        double carbGoal = goalCalories * (ratio.get("탄수화물") / 100);
        double proteinGoal = goalCalories * (ratio.get("단백질") / 100);
        double fatGoal = goalCalories * (ratio.get("지방") / 100);

        // 5️⃣ DTO 리스트 구성
        List<NutrientBarDto> bars = new ArrayList<>();
        bars.add(new NutrientBarDto("탄수화물", carbKcal, carbGoal));
        bars.add(new NutrientBarDto("단백질", proteinKcal, proteinGoal));
        bars.add(new NutrientBarDto("지방", fatKcal, fatGoal));

        return bars;
    }

}
