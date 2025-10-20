package com.example.meal_tracker.repository; 

import com.example.meal_tracker.domain.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // import 추가!

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
    
    // 특정 사용자의 모든 운동 기록을 찾기 위한 메소드 (추가!)
    List<ExerciseRecord> findAllByUserId(Long userId);
}