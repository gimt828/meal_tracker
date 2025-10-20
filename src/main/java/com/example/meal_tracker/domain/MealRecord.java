package com.example.meal_tracker.domain; // 본인 패키지 경로 확인

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MealRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_record_id")
    private Long id;

    // (중요!) User 엔티티와 관계 맺기
    @ManyToOne // 식단 기록(Many)은 하나의 유저(One)에게 속한다
    @JoinColumn(name = "user_id") // DB에 user_id 컬럼으로 연결
    private User user;

    private String foodName; // 음식 이름
    private Double calories; // 칼로리
    private Double protein;  // 단백질
    private Double fat;      // 지방
    private Double carbs;    // 탄수화물

    private String mealType; // 아침, 점심, 저녁

    private LocalDateTime createdAt; // 기록 시간

    // 생성 시 자동으로 현재 시간을 기록
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}