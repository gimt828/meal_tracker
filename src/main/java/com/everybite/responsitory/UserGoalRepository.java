package com.everybite.repository;

import com.everybite.entity.UserGoal; // 사용자 목표 칼로리 관련 엔티티 import.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {

    //사용자 ID로 해당 사용자의 목표 칼로리 조회.
    UserGoal findByUserId(Long userId);
}
