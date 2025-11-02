package com.everybite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Column(nullable = false, unique = true)
    private String foodName; // 음식 이름

    @Column(nullable = false)
    private double calories; // 칼로리 (kcal)

    @Column(nullable = false)
    private double carbohydrates; // 탄수화물 (g)

    @Column(nullable = false)
    private double protein; // 단백질 (g)

    @Column(nullable = false)
    private double fat; // 지방 (g)

    @Column(nullable = false)
    private String mealType; // 아침 / 점심 / 저녁 / 간식

    @Column(nullable = true)
    private String foodStyle; // 한식 / 양식 / 중식 (선택적)

    // 기본 생성자
    public Food() {}

    // 전체 필드 생성자
    public Food(Long foodId, String foodName, double calories,
                double carbohydrates, double protein, double fat,
                String mealType, String foodStyle) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.mealType = mealType;
        this.foodStyle = foodStyle;
    }

    // Getter/Setter
    public Long getFoodId() { return foodId; }
    public void setFoodId(Long foodId) { this.foodId = foodId; }

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

    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public String getFoodStyle() { return foodStyle; }
    public void setFoodStyle(String foodStyle) { this.foodStyle = foodStyle; }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", protein=" + protein +
                ", fat=" + fat +
                ", mealType='" + mealType + '\'' +
                ", foodStyle='" + foodStyle + '\'' +
                '}';
    }
}

// 수정 예정 (나트륨, 당, 콜레스테롤이 안 들어감)
