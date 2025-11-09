package com.everybite.dto;

import com.everybite.entity.UserGoal_DevA; // 수정됨!
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class UserGoalResponse_DevA {

    private Long id;
    private Long userId;
    private Double targetWeight;
    private Integer targetCalories;
    private LocalDate startDate;
    private LocalDate endDate;

    // 생성자 파라미터도 UserGoal_DevA로 변경!
    public UserGoalResponse_DevA(UserGoal_DevA goal) {
        this.id = goal.getId();
        this.userId = goal.getUser().getId();
        this.targetWeight = goal.getTargetWeight();
        this.targetCalories = goal.getTargetCalories();
        this.startDate = goal.getStartDate();
        this.endDate = goal.getEndDate();
    }
}