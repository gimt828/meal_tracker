package com.everybite.repository;

import com.everybite.entity.MealIntakeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealIntakeRecordRepository extends JpaRepository<MealIntakeRecord, Long> {
    List<MealIntakeRecord> findByDate(LocalDate date);
    List<MealIntakeRecord> findByDateBetween(LocalDate start, LocalDate end);

}
