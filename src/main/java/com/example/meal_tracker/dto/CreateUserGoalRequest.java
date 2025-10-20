package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateUserGoalRequest {
    
    private Long userId; // 어느 유저의 목표인지
    
    private Double targetWeight;
    private Integer targetCalories;
    private LocalDate startDate;
    private LocalDate endDate;
}