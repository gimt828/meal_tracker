package com.example.meal_tracker.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 고유 ID

    @Column(nullable = false, unique = true)
    private String nickname; // 닉네임

    private Double height; // 키

    private Double weight; // 체중

    private LocalDate birthDate; // 생년월일

    private String gender; // 성별

    @Column(name = "diet_goal") // ✅ 이 부분이 추가되었습니다.
    private String dietGoal; // 사용자의 식단 목표 (e.g., DIET, BULK_UP)
}