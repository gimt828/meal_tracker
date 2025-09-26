package com.example.meal_tracker.repository;

import com.example.meal_tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이 안에 아무것도 적지 않아도 save, findById 같은 기능이 자동으로 생깁니다.
}
