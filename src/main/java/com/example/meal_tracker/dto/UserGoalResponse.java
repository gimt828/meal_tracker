package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.UserGoal;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class UserGoalResponse {

    private Long id;
    private Long userId;
    private Double targetWeight;
    private Integer targetCalories;
    private LocalDate startDate;
    private LocalDate endDate;

    // UserGoal 엔티티를 DTO로 변환하는 생성자
    public UserGoalResponse(UserGoal goal) {
        this.id = goal.getId();
        this.userId = goal.getUser().getId(); // User 객체에서 ID만 꺼냄
        this.targetWeight = goal.getTargetWeight();
        this.targetCalories = goal.getTargetCalories();
        this.startDate = goal.getStartDate();
        this.endDate = goal.getEndDate();
    }
}