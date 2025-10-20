package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseRecordRequest {
    private Long userId;
    private String exerciseName;
    private Integer durationMinutes;
    private Double caloriesBurned;
}