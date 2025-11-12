package com.everybite.repository;

import com.everybite.entity.UserGoal_DevA; // 수정됨!
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserGoalRepository_DevA extends JpaRepository<UserGoal_DevA, Long> {

    Optional<UserGoal_DevA> findByUserId(Long userId);
}