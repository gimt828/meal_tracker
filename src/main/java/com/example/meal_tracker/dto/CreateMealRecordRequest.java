package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMealRecordRequest {

    // (중요!) 어떤 유저가 기록했는지 ID를 받아야 합니다.
    private Long userId;

    private String foodName;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbs;
    private String mealType; // "아침", "점심", "저녁"
}