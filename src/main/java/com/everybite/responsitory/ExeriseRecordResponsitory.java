package com.everybite.repository;

import com.everybite.entity.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {
    List<ExerciseRecord> findByDate(LocalDate date);
    List<ExerciseRecord> findByDateBetween(LocalDate start, LocalDate end);

}
