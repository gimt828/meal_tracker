package com.everybite.service;

import com.everybite.dto.CreateExerciseRecordRequest_DevA;
import com.everybite.dto.CreateMealRecordRequest_DevA;
import com.everybite.entity.ExerciseRecord_DevA;
import com.everybite.entity.MealRecord_DevA;
import com.everybite.entity.User_DevA;
import com.everybite.repository.ExerciseRecordRepository_DevA; // ⚠️ 폴더명 repository가 맞는지 확인!
import com.everybite.repository.MealRecordRepository_DevA;   // ⚠️ 폴더명 repository가 맞는지 확인!
import com.everybite.repository.UserRepository_DevA;     // ⚠️ 폴더명 repository가 맞는지 확인!
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService_DevA {

    private final MealRecordRepository_DevA mealRecordRepository;
    private final UserRepository_DevA userRepository;
    private final ExerciseRecordRepository_DevA exerciseRecordRepository;

    // --- 식단 기록 (Meal Record) ---

    @Transactional
    public MealRecord_DevA createMealRecord(CreateMealRecordRequest_DevA request) {
        User_DevA user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        MealRecord_DevA newRecord = new MealRecord_DevA();
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
    public List<MealRecord_DevA> findMealRecordsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return mealRecordRepository.findAllByUserId(userId);
    }

    @Transactional
    public MealRecord_DevA updateMealRecord(Long recordId, CreateMealRecordRequest_DevA request) {
        MealRecord_DevA record = mealRecordRepository.findById(recordId)
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
            throw new IllegalArgumentException("Meal record not found with id: "D" + recordId);
        }
        mealRecordRepository.deleteById(recordId);
    }

    // --- 운동 기록 (Exercise Record) ---

    @Transactional
    public ExerciseRecord_DevA createExerciseRecord(CreateExerciseRecordRequest_DevA request) {
        User_DevA user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        ExerciseRecord_DevA newRecord = new ExerciseRecord_DevA();
        newRecord.setUser(user);
        newRecord.setExerciseName(request.getExerciseName());
        newRecord.setDurationMinutes(request.getDurationMinutes());

        // --- ⬇️ 여기가 수정되었습니다! ⬇️ ---
        // newRecord.setCaloriesBurned(request.getCaloriesBurned()); // DTO에서 필드 삭제했으므로 이 줄 삭제!

        // (새로 추가할 계산 로직 - 아주 간단한 예시!)
        // 예: 걷기는 1분당 3.5 칼로리, 달리기는 1분당 7 칼로리 소모한다고 가정
        double calories = 0;
        if ("걷기".equals(request.getExerciseName())) {
            calories = request.getDurationMinutes() * 3.5;
        } else if ("달리기".equals(request.getExerciseName())) {
            calories = request.getDurationMinutes() * 7.0;
        } else {
            // 모르는 운동은 일단 시간당 5 칼로리 소모로 기본값 설정 (나중에 수정 필요)
            calories = request.getDurationMinutes() * 5.0; 
        }
        newRecord.setCaloriesBurned(calories); // 계산된 칼로리 저장
        // --- ⬆️ 여기까지 수정되었습니다! ⬆️ ---

        return exerciseRecordRepository.save(newRecord);
    }

    @Transactional(readOnly = true)
    public List<ExerciseRecord_DevA> findExerciseRecordsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return exerciseRecordRepository.findAllByUserId(userId);
    }

    @Transactional
    public ExerciseRecord_DevA updateExerciseRecord(Long recordId, CreateExerciseRecordRequest_DevA request) {
        ExerciseRecord_DevA record = exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise record not found with id: " + recordId));

        record.setExerciseName(request.getExerciseName());
        record.setDurationMinutes(request.getDurationMinutes());
        
        // --- ⬇️ 여기도 수정되었습니다! (수정 시에도 칼로리 재계산) ⬇️ ---
        // newRecord.setCaloriesBurned(request.getCaloriesBurned()); // DTO에 필드 없음!

        // (계산 로직 추가)
        double calories = 0;
        if ("걷기".equals(request.getExerciseName())) {
            calories = request.getDurationMinutes() * 3.5;
        } else if ("달리기".equals(request.getExerciseName())) {
            calories = request.getDurationMinutes() * 7.0;
        } else {
            calories = request.getDurationMinutes() * 5.0; 
        }
        record.setCaloriesBurned(calories); // 계산된 칼로리 저장
        // --- ⬆️ 여기까지 수정되었습니다! ⬆️ ---

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