package com.everybite.dto;

import com.everybite.entity.MealRecord_DevA; // 수정됨!
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MealRecordResponse_DevA { // 클래스명 _DevA로 통일

    private Long id;
    private String foodName;
    private Double calories;
    private String mealType;
    private LocalDateTime createdAt;
    private Long userId;

    public MealRecordResponse_DevA(MealRecord_DevA record) {
        this.id = record.getId();
        this.foodName = record.getFoodName();
        this.calories = record.getCalories();
        this.mealType = record.getMealType();
        this.createdAt = record.getCreatedAt();
        this.userId = record.getUser().getId();
    }
}