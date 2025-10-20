package com.example.meal_tracker.repository; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.MealRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // <-- import 추가!

// JpaRepository<엔티티이름, PK의타입>
public interface MealRecordRepository extends JpaRepository<MealRecord, Long> {
    
    // (나중에 '날짜별 조회' 같은 기능이 필요하면 여기에 메소드를 추가하면 됩니다.)
    
    // 특정 사용자의 모든 식단 기록을 찾기 위한 메소드 (추가!)
    List<MealRecord> findAllByUserId(Long userId);
}