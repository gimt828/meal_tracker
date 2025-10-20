package com.example.meal_tracker.dto; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String nickname;
    private String dietGoal;

    // User 엔티티를 받아서 UserResponse DTO로 변환하는 생성자
    public UserResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.dietGoal = user.getDietGoal();
    }
}
