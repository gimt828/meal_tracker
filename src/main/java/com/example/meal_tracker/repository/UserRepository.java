package com.example.meal_tracker.repository;

import com.example.meal_tracker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // 닉네임으로 중복 검사를 하기 위한 메소드
    boolean existsByNickname(String nickname);
}