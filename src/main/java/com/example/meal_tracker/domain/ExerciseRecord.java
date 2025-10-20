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
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String exerciseName; // 운동 이름
    private Integer durationMinutes; // 운동 시간 (분)
    private Double caloriesBurned; // 소모 칼로리

    private LocalDateTime createdAt; // 기록 시간

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}