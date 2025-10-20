package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.ExerciseRecord;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ExerciseRecordResponse {
    
    private Long id;
    private Long userId;
    private String exerciseName;
    private Integer durationMinutes;
    private Double caloriesBurned;
    private LocalDateTime createdAt;

    public ExerciseRecordResponse(ExerciseRecord record) {
        this.id = record.getId();
        this.userId = record.getUser().getId();
        this.exerciseName = record.getExerciseName();
        this.durationMinutes = record.getDurationMinutes();
        this.caloriesBurned = record.getCaloriesBurned();
        this.createdAt = record.getCreatedAt();
    }
}