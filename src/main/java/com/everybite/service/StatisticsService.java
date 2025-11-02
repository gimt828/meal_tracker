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

    private final MealIntakeRecordRepository mealRepo;
    private final ExerciseRecordRepository exerciseRepo;
    private final UserGoalRepository userGoalRepo;

    public StatisticsService(MealIntakeRecordRepository mealRepo,
                             ExerciseRecordRepository exerciseRepo,
                             UserGoalRepository userGoalRepo) {
        this.mealRepo = mealRepo;
        this.exerciseRepo = exerciseRepo;
        this.userGoalRepo = userGoalRepo;
    }

    // 일간 통계 계산 (user_id 내부 고정)
    public DailyStatisticsDto getDailyStatistics(LocalDate date) {
        Long user_id = 1L; // ✅ 단일 사용자 구조에서는 내부에서 고정

        List<MealIntakeRecord> meals = mealRepo.findByDate(date);
        List<ExerciseRecord> exercises = exerciseRepo.findByDate(date);

        double total_in = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        double total_out = exercises.stream().mapToDouble(ExerciseRecord::getCalories_burned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        int goal_calories = userGoalRepo.findByUserId(user_id).getTarget_calories();
        double remaining_calories = goal_calories - total_in;

        DailyStatisticsDto dto = new DailyStatisticsDto();
        dto.setDate(date);
        dto.setTotal_calories_in(total_in);
        dto.setTotal_calories_out(total_out);
        dto.setNet_calories(total_in - total_out);
        dto.setTotal_carbs(carbs);
        dto.setTotal_protein(protein);
        dto.setTotal_fat(fat);
        dto.setGoal_calories(goal_calories);
        dto.setRemaining_calories(remaining_calories);

        return dto;
    }

    // 월간 통계 계산 (단일 사용자 구조)
    public MonthlyStatisticsDto getMonthlyStatistics(int year, int month) {
        Long user_id = 1L; // ✅ 기본 사용자 고정

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<MealIntakeRecord> meals = mealRepo.findByDateBetween(start, end);
        List<ExerciseRecord> exercises = exerciseRepo.findByDateBetween(start, end);

        double total_in = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        double total_out = exercises.stream().mapToDouble(ExerciseRecord::getCalories_burned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        int goal_calories = userGoalRepo.findByUserId(user_id).getTarget_calories();
        int days_in_month = start.lengthOfMonth();
        double achievement = (total_in / (goal_calories * days_in_month)) * 100.0;

        MonthlyStatisticsDto dto = new MonthlyStatisticsDto();
        dto.setYear(year);
        dto.setMonth(month);
        dto.setTotal_calories_in(total_in);
        dto.setTotal_calories_out(total_out);
        dto.setNet_calories(total_in - total_out);
        dto.setTotal_carbs(carbs);
        dto.setTotal_protein(protein);
        dto.setTotal_fat(fat);
        dto.setGoal_calories(goal_calories);
        dto.setAchievement_rate(Math.round(achievement * 10) / 10.0);

        return dto;
    }
}
