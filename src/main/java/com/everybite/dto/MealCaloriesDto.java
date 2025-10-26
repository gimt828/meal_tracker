package com.everybite.dto;

import java.util.List;

public class MealCaloriesDto {
    private double totalGoal; // 하루 권장 칼로리
    private List<ChartDataDto> meals; // label, value 구조

    public MealCaloriesDto() {}

    public MealCaloriesDto(double totalGoal, List<ChartDataDto> meals) {
        this.totalGoal = totalGoal;
        this.meals = meals;
    }

    public double getTotalGoal() {
        return totalGoal;
    }

    public void setTotalGoal(double totalGoal) {
        this.totalGoal = totalGoal;
    }

    public List<ChartDataDto> getMeals() {
        return meals;
    }

    public void setMeals(List<ChartDataDto> meals) {
        this.meals = meals;
    }
}
