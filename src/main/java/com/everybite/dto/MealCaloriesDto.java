package com.everybite.dto;

import java.util.List;

public class MealCaloriesDto {
    private double totalGoal; // 하루 권장(목표) 칼로리
    private List<ChartDateDto> meals; // 각 식사별 칼로리 데이터 (라벨 + 값 구조)

    public MealCaloriesDto() {}

    public MealCaloriesDto(double totalGoal, List<ChartDateDto> meals) {
        this.totalGoal = totalGoal;
        this.meals = meals;
    }

    public double getTotalGoal() {
        return totalGoal;
    }

    public void setTotalGoal(double totalGoal) {
        this.totalGoal = totalGoal;
    }

    public List<ChartDateDto> getMeals() {
        return meals;
    }

    public void setMeals(List<ChartDateDto> meals) {
        this.meals = meals;
    }
}
