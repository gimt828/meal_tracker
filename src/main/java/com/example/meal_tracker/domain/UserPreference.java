package com.example.meal_tracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_preference")
@Getter
@Setter
@NoArgsConstructor
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 한 명의 유저(One)는 여러 개의 선호(Many)를 가질 수 있습니다.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 예시: "다이어트 식단", "벌크업 식단" 등
    private String preferredCuisine;

    // 예시: "닭가슴살", "계란" 등
    private String preferredFood;
}
