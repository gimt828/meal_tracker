package com.everybite.repository;

import com.everybite.entity.MealIntakeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealIntakeRecordRepository extends JpaRepository<MealIntakeRecord, Long> {

    // 기본 조회 메서드
    List<MealIntakeRecord> findByDate(LocalDate date);
    List<MealIntakeRecord> findByDateBetween(LocalDate start, LocalDate end);

    // 그래프 1의 탄단지 합계 계산용
    @Query("""
            SELECT 
                SUM(m.carbohydrates), 
                SUM(m.protein), 
                SUM(m.fat)
            FROM MealIntakeRecord m
            WHERE m.date = :date
            """)
    List<Object[]> findDailyMacroSums(@Param("date") LocalDate date);

    // 오늘 섭취 칼로리 합계
    @Query("SELECT COALESCE(SUM(m.calories), 0) FROM MealIntakeRecord m WHERE m.date = :today")
    double sumCaloriesForToday(@Param("today") LocalDate today);

    // 오늘 탄수화물 합계
    @Query("SELECT COALESCE(SUM(m.carbohydrates), 0) FROM MealIntakeRecord m WHERE m.date = :today")
    double sumCarbForToday(@Param("today") LocalDate today);

    // 오늘 단백질 합계
    @Query("SELECT COALESCE(SUM(m.protein), 0) FROM MealIntakeRecord m WHERE m.date = :today")
    double sumProteinForToday(@Param("today") LocalDate today);

    // 오늘 지방 합계
    @Query("SELECT COALESCE(SUM(m.fat), 0) FROM MealIntakeRecord m WHERE m.date = :today")
    double sumFatForToday(@Param("today") LocalDate today);

    // 최근 3일간 먹은 음식 이름 조회 (중복 방지용)
    @Query("SELECT m.foodName FROM MealIntakeRecord m WHERE m.date >= :threeDaysAgo")
    List<String> findRecentFoodNames(@Param("threeDaysAgo") LocalDate threeDaysAgo);
}
