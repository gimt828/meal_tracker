package com.example.meal_tracker.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_goal")
@Getter
@Setter
@NoArgsConstructor
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // UserGoal은 User 하나와 1:1 관계를 가집니다.
    @OneToOne
    @JoinColumn(name = "user_id") // user_id 컬럼으로 User 테이블과 연결
    private User user;

    @Column(nullable = false)
    private Double targetWeight; // 목표 체중

    @Column(nullable = false)
    private Integer targetCalories; // 목표 하루 칼로리

    private LocalDate startDate; // 시작일

    private LocalDate endDate; // 마감일
}