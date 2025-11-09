package com.everybite.responsitory;

import com.everybite.entity.MealRecord_DevA; // 수정됨!
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MealRecordRepository_DevA extends JpaRepository<MealRecord_DevA, Long> {
    
    List<MealRecord_DevA> findAllByUserId(Long userId);
}