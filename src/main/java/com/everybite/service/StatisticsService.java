package com.everybite.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.everybite.dto.DailyStatisticsDto;
import com.everybite.dto.MonthlyStatisticsDto;
import com.everybite.entity.ExerciseRecord;
import com.everybite.entity.MealIntakeRecord;
import com.everybite.repository.ExerciseRecordRepository;
import com.everybite.repository.MealIntakeRecordRepository;
import com.everybite.repository.UserGoalRepository;

@Service
public class StatisticsService {

    private final MealIntakeRecordRepository mealRepo; // 섭취 기록 조회용
    private final ExerciseRecordRepository exerciseRepo; // 운동 기록 조회용
    private final UserGoalRepository userGoalRepo; // 사용자 목표 칼로리 조회용용

    // Lombok 제거 후 수동 생성자 주입
    public StatisticsService(MealIntakeRecordRepository mealRepo,
                             ExerciseRecordRepository exerciseRepo,
                             UserGoalRepository userGoalRepo) {
        this.mealRepo = mealRepo;
        this.exerciseRepo = exerciseRepo;
        this.userGoalRepo = userGoalRepo;
    }

    // 일간 통계 계산 로직
    public DailyStatisticsDto getDailyStatistics(LocalDate date, Long userId) {
        // 해당 날짜의 식사 기록과 운동 기록 조회
        List<MealIntakeRecord> meals = mealRepo.findByDate(date);
        List<ExerciseRecord> exercises = exerciseRepo.findByDate(date);

        // 스트림을 사용하여 합계 계산
        double totalIn = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        double totalOut = exercises.stream().mapToDouble(ExerciseRecord::getCalories_burned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        // 목표 칼로리 및 남은 칼로리 계산
        int goalCalories = userGoalRepo.findByUserId(userId).getTarget_calories();
        double remainingCalories = goalCalories - totalIn;

        // DTO 객체에 데이터 매핑
        DailyStatisticsDto dto = new DailyStatisticsDto();
        dto.setDate(date);
        dto.setTotalCaloriesIn(totalIn);
        dto.setTotalCaloriesOut(totalOut);
        dto.setNetCalories(totalIn - totalOut);
        dto.setTotalCarbs(carbs);
        dto.setTotalProtein(protein);
        dto.setTotalFat(fat);
        dto.setGoalCalories(goalCalories);
        dto.setRemainingCalories(remainingCalories);

        // Controller로 반환 (-> JSON 응답)
        return dto;
    }

    // 월간 통계 계산
    public MonthlyStatisticsDto getMonthlyStatistics(int year, int month, Long userId) {
        
        // 월 시작일과 마지막 날 계산
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // 해당 월 범위의 식사/운동 기록 조회
        List<MealIntakeRecord> meals = mealRepo.findByDateBetween(start, end);
        List<ExerciseRecord> exercises = exerciseRepo.findByDateBetween(start, end);

        //전체 합계 계산
        double totalIn = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        double totalOut = exercises.stream().mapToDouble(ExerciseRecord::getCalories_burned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        //목표 칼로리 및 달성률 계산
        int goalCalories = userGoalRepo.findByUserId(userId).getTarget_calories();
        int daysInMonth = start.lengthOfMonth();
        double achievement = (totalIn / (goalCalories * daysInMonth)) * 100.0;

        //DTO 객체 생성 및 값 매핑
        MonthlyStatisticsDto dto = new MonthlyStatisticsDto();
        dto.setYear(year);
        dto.setMonth(month);
        dto.setTotalCaloriesIn(totalIn);
        dto.setTotalCaloriesOut(totalOut);
        dto.setNetCalories(totalIn - totalOut);
        dto.setTotalCarbs(carbs);
        dto.setTotalProtein(protein);
        dto.setTotalFat(fat);
        dto.setGoalCalories(goalCalories);
        dto.setAchievementRate(Math.round(achievement * 10) / 10.0);

        return dto;
    }
}
