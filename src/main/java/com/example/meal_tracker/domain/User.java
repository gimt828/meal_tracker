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
@Table(name = "users") // 테이블 이름을 'user'가 아닌 'users'로 지정 (user는 예약어인 경우가 많음)
@Getter
@Setter
@NoArgsConstructor // JPA는 기본 생성자가 꼭 필요해요!
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
}

