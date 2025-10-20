package com.example.meal_tracker.service; 

import com.example.meal_tracker.domain.ExerciseRecord; 
import com.example.meal_tracker.domain.MealRecord;
import com.example.meal_tracker.domain.User;
import com.example.meal_tracker.dto.CreateExerciseRecordRequest; // 수정에도 이 DTO를 재사용합니다.
import com.example.meal_tracker.dto.CreateMealRecordRequest; 
import com.example.meal_tracker.repository.ExerciseRecordRepository; 
import com.example.meal_tracker.repository.MealRecordRepository;
import com.example.meal_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final MealRecordRepository mealRecordRepository;
    private final UserRepository userRepository;
    private final ExerciseRecordRepository exerciseRecordRepository; 

    // --- 식단 기록 (Meal Record) ---

    @Transactional
    public MealRecord createMealRecord(CreateMealRecordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        MealRecord newRecord = new MealRecord();
        newRecord.setUser(user); 
        newRecord.setFoodName(request.getFoodName());
        newRecord.setCalories(request.getCalories());
        newRecord.setProtein(request.getProtein());
        newRecord.setFat(request.getFat());
        newRecord.setCarbs(request.getCarbs());
        newRecord.setMealType(request.getMealType());

        return mealRecordRepository.save(newRecord);
    }

    @Transactional(readOnly = true)
    public List<MealRecord> findMealRecordsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return mealRecordRepository.findAllByUserId(userId);
    }
    
    @Transactional
    public MealRecord updateMealRecord(Long recordId, CreateMealRecordRequest request) {
        MealRecord record = mealRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Meal record not found with id: " + recordId));

        record.setFoodName(request.getFoodName());
        record.setCalories(request.getCalories());
        record.setProtein(request.getProtein());
        record.setFat(request.getFat());
        record.setCarbs(request.getCarbs());
        record.setMealType(request.getMealType());

        return mealRecordRepository.save(record);
    }

    @Transactional
    public void deleteMealRecord(Long recordId) {
        if (!mealRecordRepository.existsById(recordId)) {
            throw new IllegalArgumentException("Meal record not found with id: " + recordId);
        }
        mealRecordRepository.deleteById(recordId);
    }

    // --- 운동 기록 (Exercise Record) ---
    
    @Transactional
    public ExerciseRecord createExerciseRecord(CreateExerciseRecordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        ExerciseRecord newRecord = new ExerciseRecord();
        newRecord.setUser(user);
        newRecord.setExerciseName(request.getExerciseName());
        newRecord.setDurationMinutes(request.getDurationMinutes());
        newRecord.setCaloriesBurned(request.getCaloriesBurned());

        return exerciseRecordRepository.save(newRecord);
    }

    @Transactional(readOnly = true)
    public List<ExerciseRecord> findExerciseRecordsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return exerciseRecordRepository.findAllByUserId(userId);
    }

    // (새로 추가된 메소드!)
    @Transactional
    public ExerciseRecord updateExerciseRecord(Long recordId, CreateExerciseRecordRequest request) {
        // 1. 기존 기록 찾기
        ExerciseRecord record = exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise record not found with id: " + recordId));
        
        // (선택 사항: 사용자 ID 일치 여부 확인)
        
        // 2. 요청 DTO의 데이터로 필드 업데이트
        record.setExerciseName(request.getExerciseName());
        record.setDurationMinutes(request.getDurationMinutes());
        record.setCaloriesBurned(request.getCaloriesBurned());
        // Note: user, createdAt은 업데이트 X

        // 3. 업데이트된 기록 저장
        return exerciseRecordRepository.save(record);
    }

    @Transactional
    public void deleteExerciseRecord(Long recordId) {
        if (!exerciseRecordRepository.existsById(recordId)) {
            throw new IllegalArgumentException("Exercise record not found with id: " + recordId);
        }
        exerciseRecordRepository.deleteById(recordId);
    }
}