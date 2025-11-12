package com.everybite.repository;

import com.everybite.entity.ExerciseRecord_DevA; // 수정됨!
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExerciseRecordRepository_DevA extends JpaRepository<ExerciseRecord_DevA, Long> {
    
    List<ExerciseRecord_DevA> findAllByUserId(Long userId);
}