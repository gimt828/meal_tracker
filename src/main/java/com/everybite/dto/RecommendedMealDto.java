package com.everybite.dto;

import com.everybite.entity.Food;

public class RecommendedMealDto {

    private String foodName;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
    private double score;

    // 기본 생성자
    public RecommendedMealDto() {}

    // 전체 필드 생성자
    public RecommendedMealDto(String foodName, double calories,
                              double carbohydrates, double protein, double fat,
                              double score) {
        this.foodName = foodName;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.score = score;
    }

    // Food 객체로부터 생성
    public RecommendedMealDto(Food food, double score) {
        this.foodName = food.getFoodName();
        this.calories = food.getCalories();
        this.carbohydrates = food.getCarbohydrates();
        this.protein = food.getProtein();
        this.fat = food.getFat();
        this.score = score;
    }

    // Getter/Setter
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(double carbohydrates) { this.carbohydrates = carbohydrates; }

    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }

    public double getFat() { return fat; }
    public void setFat(double fat) { this.fat = fat; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return "RecommendedMealDto{" +
                "foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                ", score=" + score +
                '}';
    }
}
