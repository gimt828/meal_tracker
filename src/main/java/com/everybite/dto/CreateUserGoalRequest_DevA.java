package com.everybite.dto; // ✅ 패키지명 확인!

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateUserGoalRequest_DevA {
    
    private Long userId; // 어느 유저의 목표인지
    
    private Double targetWeight;
    private Integer targetCalories;
    private LocalDate startDate;
    private LocalDate endDate;
    
    private String dietType; // "DIET", "BULK_UP" 등
    private Double customCarbRatio;
    private Double customProteinRatio;
    private Double customFatRatio;
}