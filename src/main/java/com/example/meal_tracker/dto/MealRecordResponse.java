package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.MealRecord;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MealRecordResponse {

    private Long id;
    private String foodName;
    private Double calories;
    private String mealType;
    private LocalDateTime createdAt;
    private Long userId; // 이 기록이 누구의 것인지 ID만 포함

    // MealRecord 엔티티를 DTO로 변환하는 생성자
    public MealRecordResponse(MealRecord record) {
        this.id = record.getId();
        this.foodName = record.getFoodName();
        this.calories = record.getCalories();
        this.mealType = record.getMealType();
        this.createdAt = record.getCreatedAt();
        this.userId = record.getUser().getId(); // User 객체에서 ID만 꺼냄
    }
}