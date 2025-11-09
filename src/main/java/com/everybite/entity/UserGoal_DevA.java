package com.everybite.entity;

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
public class UserGoal_DevA { // 클래스명 변경

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User_DevA user; // User_DevA 로 변경

    @Column(nullable = false)
    private Double targetWeight;
    @Column(nullable = false)
    private Integer targetCalories;
    private LocalDate startDate;
    private LocalDate endDate;
}