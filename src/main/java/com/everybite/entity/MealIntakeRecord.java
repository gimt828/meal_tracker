package com.everybite.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meal_intake_record")
public class MealIntakeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intake_id;

    @Column(nullable = false)
    private LocalDate date; // 식사 날짜

    @Column(nullable = false)
    private String food_name; // 음식 이름

    @Column(nullable = false)
    private double calories; // 섭취 칼로리(kcal)

    private double carbohydrates; // 탄수화물(g)
    private double protein;       // 단백질(g)
    private double fat;           // 지방(g)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType meal_type; // 아침, 점심, 저녁, 간식

    // 기본 생성자
    public MealIntakeRecord() {}

    // 전체 필드 생성자
    public MealIntakeRecord(Long intake_id, LocalDate date, String food_name, double calories,
                            double carbohydrates, double protein, double fat, MealType meal_type) {
        this.intake_id = intake_id;
        this.date = date;
        this.food_name = food_name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.meal_type = meal_type;
    }

    // Getter / Setter
    public Long getIntake_id() {
        return intake_id;
    }

    public void setIntake_id(Long intake_id) {
        this.intake_id = intake_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public MealType getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(MealType meal_type) {
        this.meal_type = meal_type;
    }

    // Optional: toString() (디버깅용)
    @Override
    public String toString() {
        return "MealIntakeRecord{" +
                "intake_id=" + intake_id +
                ", date=" + date +
                ", food_name='" + food_name + '\'' +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                ", meal_type=" + meal_type +
                '}';
    }
}
