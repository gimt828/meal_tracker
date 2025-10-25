package com.everybite.repository;

import com.everybite.entity.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
    UserGoal findByUserId(Long userId);
}
