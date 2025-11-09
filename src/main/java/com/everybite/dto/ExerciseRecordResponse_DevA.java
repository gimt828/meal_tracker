package com.everybite.dto;

import com.everybite.entity.ExerciseRecord_DevA; // 수정됨!
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ExerciseRecordResponse_DevA {
    
    private Long id;
    private Long userId;
    private String exerciseName;
    private Integer durationMinutes;
    private Double caloriesBurned;
    private LocalDateTime createdAt;

    public ExerciseRecordResponse_DevA(ExerciseRecord_DevA record) {
        this.id = record.getId();
        this.userId = record.getUser().getId();
        this.exerciseName = record.getExerciseName();
        this.durationMinutes = record.getDurationMinutes();
        this.caloriesBurned = record.getCaloriesBurned();
        this.createdAt = record.getCreatedAt();
    }
}