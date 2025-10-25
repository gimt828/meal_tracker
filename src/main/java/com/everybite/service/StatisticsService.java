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

    // Lombok 제거 후 수동 생성자 주입
    public StatisticsService(MealIntakeRecordRepository mealRepo,
                             ExerciseRecordRepository exerciseRepo,
                             UserGoalRepository userGoalRepo) {
        this.mealRepo = mealRepo;
        this.exerciseRepo = exerciseRepo;
        this.userGoalRepo = userGoalRepo;
    }

    // 일간 통계 계산
    public DailyStatisticsDto getDailyStatistics(LocalDate date, Long userId) {
        List<MealIntakeRecord> meals = mealRepo.findByDate(date);
        List<ExerciseRecord> exercises = exerciseRepo.findByDate(date);

        double totalIn = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        double totalOut = exercises.stream().mapToDouble(ExerciseRecord::getCalories_burned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        int goalCalories = userGoalRepo.findByUserId(userId).getTarget_calories();
        double remainingCalories = goalCalories - totalIn;

        // Lombok builder 제거 → 수동 생성자 or setter 사용
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
        List<ExerciseRecord> exercises = exerciseRepo.findByDateBetween(start, end);

        double totalIn = meals.stream().mapToDouble(MealIntakeRecord::getCalories).sum();
        double totalOut = exercises.stream().mapToDouble(ExerciseRecord::getCalories_burned).sum();
        double carbs = meals.stream().mapToDouble(MealIntakeRecord::getCarbohydrates).sum();
        double protein = meals.stream().mapToDouble(MealIntakeRecord::getProtein).sum();
        double fat = meals.stream().mapToDouble(MealIntakeRecord::getFat).sum();

        int goalCalories = userGoalRepo.findByUserId(userId).getTarget_calories();
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
