package com.example.meal_tracker.controller; 

import com.example.meal_tracker.domain.MealRecord;
import com.example.meal_tracker.dto.CreateMealRecordRequest; 
import com.example.meal_tracker.dto.MealRecordResponse;
import com.example.meal_tracker.domain.ExerciseRecord; 
import com.example.meal_tracker.dto.CreateExerciseRecordRequest; // 수정에도 재사용
import com.example.meal_tracker.dto.ExerciseRecordResponse; 
import com.example.meal_tracker.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records") 
public class RecordController {

    private final RecordService recordService;

    // --- 식단 기록 API ---

    // POST /api/records/meals (생성)
    @PostMapping("/meals")
    public ResponseEntity<MealRecordResponse> createMealRecord(
            @RequestBody CreateMealRecordRequest request) {
        
        MealRecord savedRecord = recordService.createMealRecord(request);
        return ResponseEntity.ok(new MealRecordResponse(savedRecord));
    }
    
    // GET /api/records/meals/user/{userId} (사용자별 전체 조회)
    @GetMapping("/meals/user/{userId}")
    public ResponseEntity<List<MealRecordResponse>> getMealRecordsByUserId(@PathVariable("userId") Long userId) { 
        
        List<MealRecord> records = recordService.findMealRecordsByUserId(userId);
        List<MealRecordResponse> responseList = records.stream()
                .map(MealRecordResponse::new) 
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responseList);
    }

    // PUT /api/records/meals/{recordId} (수정)
    @PutMapping("/meals/{recordId}")
    public ResponseEntity<MealRecordResponse> updateMealRecord(
            @PathVariable("recordId") Long recordId,
            @RequestBody CreateMealRecordRequest request) { 
        
        MealRecord updatedRecord = recordService.updateMealRecord(recordId, request);
        
        return ResponseEntity.ok(new MealRecordResponse(updatedRecord));
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
    public ResponseEntity<ExerciseRecordResponse> createExerciseRecord(
            @RequestBody CreateExerciseRecordRequest request) {
                
        ExerciseRecord savedRecord = recordService.createExerciseRecord(request);
        
        return ResponseEntity.ok(new ExerciseRecordResponse(savedRecord));
    }

    // GET /api/records/exercises/user/{userId} (사용자별 전체 조회)
    @GetMapping("/exercises/user/{userId}")
    public ResponseEntity<List<ExerciseRecordResponse>> getExerciseRecordsByUserId(@PathVariable("userId") Long userId) {
        
        List<ExerciseRecord> records = recordService.findExerciseRecordsByUserId(userId);
        List<ExerciseRecordResponse> responseList = records.stream()
                .map(ExerciseRecordResponse::new)
                .collect(Collectors.toList());
            
        return ResponseEntity.ok(responseList);
    }

    // (새로 추가된 메소드!)
    // PUT /api/records/exercises/{recordId} (수정)
    @PutMapping("/exercises/{recordId}")
    public ResponseEntity<ExerciseRecordResponse> updateExerciseRecord(
            @PathVariable("recordId") Long recordId,
            @RequestBody CreateExerciseRecordRequest request) { // Create DTO 재사용
        
        ExerciseRecord updatedRecord = recordService.updateExerciseRecord(recordId, request);
        
        return ResponseEntity.ok(new ExerciseRecordResponse(updatedRecord));
    }

    // DELETE /api/records/exercises/{recordId} (삭제)
    @DeleteMapping("/exercises/{recordId}")
    public ResponseEntity<Void> deleteExerciseRecord(@PathVariable("recordId") Long recordId) {
        
        recordService.deleteExerciseRecord(recordId);
        
        return ResponseEntity.ok().build(); 
    }
}