package com.everybite.repository;

import com.everybite.entity.MealIntakeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealIntakeRecordRepository extends JpaRepository<MealIntakeRecord, Long> {

    // 특정 날짜의 섭취 기록 조회
    List<MealIntakeRecord> findByDate(LocalDate date);

    // 날짜 범위 내의 섭취 기록 조회 (start ~ end)
    List<MealIntakeRecord> findByDateBetween(LocalDate start, LocalDate end);

    //그래프 1의 탄단지 합계 계산용
    @Query("""
            SELECT 
                SUM(m.carbohydrates), 
                SUM(m.protein), 
                SUM(m.fat)
            FROM MealIntakeRecord m
            WHERE m.date = :date
        """)
        List<Object[]> findDailyMacroSums(@Param("date") LocalDate date);
}
