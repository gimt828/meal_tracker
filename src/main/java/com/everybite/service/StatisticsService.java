package com.everybite.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.everybite.dto.DailyStatisticsDto;
import com.everybite.dto.MonthlyStatisticsDto;
import com.everybite.entity.ExerciseRecord_DevA;
import com.everybite.entity.MealIntakeRecord;
import com.everybite.entity.UserGoal_DevA; // ✅ 추가됨

import com.everybite.repository.ExerciseRecordRepository_DevA;
import com.everybite.repository.MealIntakeRecordRepository;
import com.everybite.repository.UserGoalRepository_DevA;

@Service
public class StatisticsService {

    private final MealIntakeRecordRepository mealRepo;
    private final ExerciseRecordRepository_DevA exerciseRepo;
    private final UserGoalRepository_DevA userGoalRepo;

    public StatisticsService(MealIntakeRecordRepository mealRepo,
                             ExerciseRecordRepository_DevA exerciseRepo,
                             UserGoalRepository_DevA userGoalRepo) {
        this.mealRepo = mealRepo;
        this.exerciseRepo = exerciseRepo;
        this.userGoalRepo = userGoalRepo;
    }

    // 일간 통계 계산 로직
    public DailyStatisticsDto getDailyStatistics(LocalDate date, Long userId) {
        List<MealIntakeRecord> meals = mealRepo.findByDate(date);
        List<ExerciseRecord_DevA> exercises = exerciseRepo.findByDate(date);

        double totalIn = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        // ✅ 수정됨: getCaloriesBurned() (카멜 표기법) 사용. 에러나면 getCalories_burned()로 변경.
        double totalOut = exercises.stream().mapToDouble(ExerciseRecord_DevA::getCaloriesBurned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        // ✅ 수정됨: UserGoal_DevA 사용 및 Optional 처리
        UserGoal_DevA userGoal = userGoalRepo.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Goal not found"));
        int goalCalories = userGoal.getTargetCalories(); // ✅ 수정됨: getTargetCalories() 사용
        double remainingCalories = goalCalories - totalIn;

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

        return dto;
    }

    // 월간 통계 계산
    public MonthlyStatisticsDto getMonthlyStatistics(int year, int month, Long userId) {
        
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<MealIntakeRecord> meals = mealRepo.findByDateBetween(start, end);
        List<ExerciseRecord_DevA> exercises = exerciseRepo.findByDateBetween(start, end);

        double totalIn = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        // ✅ 수정됨: getCaloriesBurned() 사용
        double totalOut = exercises.stream().mapToDouble(ExerciseRecord_DevA::getCaloriesBurned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        // ✅ 수정됨: UserGoal_DevA 사용 및 Optional 처리
        UserGoal_DevA userGoal = userGoalRepo.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("Goal not found"));
        int goalCalories = userGoal.getTargetCalories(); // ✅ 수정됨: getTargetCalories() 사용
        int daysInMonth = start.lengthOfMonth();
        double achievement = (totalIn / (goalCalories * daysInMonth)) * 100.0;

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