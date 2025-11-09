package com.everybite.responsitory;

import com.everybite.entity.User_DevA; // 수정됨!
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository_DevA extends JpaRepository<User_DevA, Long> {
    
    boolean existsByNickname(String nickname);
}