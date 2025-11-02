package com.everybite.controller;

import com.everybite.entity.ExerciseRecord;
import com.everybite.repository.ExerciseRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private final ExerciseRecordRepository exerciseRecordRepository;

    public ExerciseController(ExerciseRecordRepository exerciseRecordRepository) {
        this.exerciseRecordRepository = exerciseRecordRepository;
    }

    // 운동 기록 등록
    @PostMapping
    public ExerciseRecord saveExercise(@RequestBody ExerciseRecord record) {
        return exerciseRecordRepository.save(record);
    }

    // 특정 날짜 운동 기록 조회
    @GetMapping
    public List<ExerciseRecord> getExercises(@RequestParam LocalDate date) {
        return exerciseRecordRepository.findByDate(date);
    }

    // 오늘 운동 칼로리 합계
    @GetMapping("/calories")
    public double getTodayBurnedCalories() {
        return exerciseRecordRepository.sumCaloriesBurnedForToday(LocalDate.now());
    }
}
