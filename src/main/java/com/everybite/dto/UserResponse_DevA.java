package com.everybite.dto;

import com.everybite.entity.User_DevA; // 수정됨!
import lombok.Getter;

@Getter
public class UserResponse_DevA {
    private Long id;
    private String nickname;
    private String dietGoal;

    public UserResponse_DevA(User_DevA user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.dietGoal = user.getDietGoal();
    }
}