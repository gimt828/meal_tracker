package com.everybite.dto; // ✅ 패키지명 확인!

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseRecordRequest_DevA {
    private Long userId;
    private String exerciseName;
    private Integer durationMinutes;
    // private Double caloriesBurned; // ✅ 서버에서 계산하므로 삭제!
}