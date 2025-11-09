package com.everybite.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateUserRequest_DevA {
    private String nickname;
    private Double height;
    private Double weight;
    private LocalDate birthDate;
    private String gender;
    private String dietGoal; // ✅ 이 필드를 추가했습니다.
}