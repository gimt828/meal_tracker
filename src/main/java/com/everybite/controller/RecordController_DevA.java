package com.everybite.controller;

import com.everybite.dto.CreateExerciseRecordRequest_DevA;
import com.everybite.dto.CreateMealRecordRequest_DevA;
import com.everybite.dto.ExerciseRecordResponse_DevA;
import com.everybite.dto.MealRecordResponse_DevA;
import com.everybite.entity.ExerciseRecord_DevA;
import com.everybite.entity.MealRecord_DevA;
import com.everybite.service.RecordService_DevA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController_DevA {

    private final RecordService_DevA recordService;

    // --- 식단 기록 API ---

    // POST /api/records/meals (생성)
    @PostMapping("/meals")
    public ResponseEntity<MealRecordResponse_DevA> createMealRecord(
            @RequestBody CreateMealRecordRequest_DevA request) {

        MealRecord_DevA savedRecord = recordService.createMealRecord(request);
        return ResponseEntity.ok(new MealRecordResponse_DevA(savedRecord));
    }

    // GET /api/records/meals/user/{userId} (사용자별 전체 조회)
    @GetMapping("/meals/user/{userId}")
    public ResponseEntity<List<MealRecordResponse_DevA>> getMealRecordsByUserId(@PathVariable("userId") Long userId) {

        List<MealRecord_DevA> records = recordService.findMealRecordsByUserId(userId);
        List<MealRecordResponse_DevA> responseList = records.stream()
                .map(MealRecordResponse_DevA::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // PUT /api/records/meals/{recordId} (수정)
    @PutMapping("/meals/{recordId}")
    public ResponseEntity<MealRecordResponse_DevA> updateMealRecord(
            @PathVariable("recordId") Long recordId,
            @RequestBody CreateMealRecordRequest_DevA request) {

        MealRecord_DevA updatedRecord = recordService.updateMealRecord(recordId, request);

        return ResponseEntity.ok(new MealRecordResponse_DevA(updatedRecord));
    }

    // DELETE /api/records/meals/{recordId} (삭제)
    @DeleteMapping("/meals/{recordId}")
    public ResponseEntity<Void> deleteMealRecord(@PathVariable("recordId") Long recordId) {

        recordService.deleteMealRecord(recordId);
        return ResponseEntity.ok().build();
    }

    // --- 운동 기록 API ---

    // POST /api/records/exercises (생성)
    @PostMapping("/exercises")
    public ResponseEntity<ExerciseRecordResponse_DevA> createExerciseRecord(
            @RequestBody CreateExerciseRecordRequest_DevA request) {

        ExerciseRecord_DevA savedRecord = recordService.createExerciseRecord(request);

        return ResponseEntity.ok(new ExerciseRecordResponse_DevA(savedRecord));
    }

    // GET /api/records/exercises/user/{userId} (사용자별 전체 조회)
    @GetMapping("/exercises/user/{userId}")
    public ResponseEntity<List<ExerciseRecordResponse_DevA>> getExerciseRecordsByUserId(@PathVariable("userId") Long userId) {

        List<ExerciseRecord_DevA> records = recordService.findExerciseRecordsByUserId(userId);
        List<ExerciseRecordResponse_DevA> responseList = records.stream()
                .map(ExerciseRecordResponse_DevA::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // PUT /api/records/exercises/{recordId} (수정)
    @PutMapping("/exercises/{recordId}")
    public ResponseEntity<ExerciseRecordResponse_DevA> updateExerciseRecord(
            @PathVariable("recordId") Long recordId,
            @RequestBody CreateExerciseRecordRequest_DevA request) {

        ExerciseRecord_DevA updatedRecord = recordService.updateExerciseRecord(recordId, request);

        return ResponseEntity.ok(new ExerciseRecordResponse_DevA(updatedRecord));
    }

    // DELETE /api/records/exercises/{recordId} (삭제)
    @DeleteMapping("/exercises/{recordId}")
    public ResponseEntity<Void> deleteExerciseRecord(@PathVariable("recordId") Long recordId) {

        recordService.deleteExerciseRecord(recordId);

        return ResponseEntity.ok().build();
    }
}