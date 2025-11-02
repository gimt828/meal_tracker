package com.everybite.repository;

import com.everybite.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
    List<ExerciseRecord> findByDate(LocalDate date);
    List<ExerciseRecord> findByDateBetween(LocalDate start, LocalDate end);
    
    // 오늘 운동 소모 칼로리 합계
    @Query("SELECT COALESCE(SUM(e.calories_burned), 0) FROM ExerciseRecord e WHERE e.date = :today")
    double sumCaloriesBurnedForToday(@Param("today") LocalDate today);

}
