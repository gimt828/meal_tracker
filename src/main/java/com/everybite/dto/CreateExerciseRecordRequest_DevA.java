package com.everybite.dto; // 본인 패키지 경로 확인

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseRecordRequest_DevA {
    private Long userId;
    private String exerciseName;
    private Integer durationMinutes;
    private Double caloriesBurned;
}