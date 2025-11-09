package com.everybite.service;

import com.everybite.dto.CreateExerciseRecordRequest_DevA;
import com.everybite.dto.CreateMealRecordRequest_DevA;
import com.everybite.entity.ExerciseRecord_DevA;
import com.everybite.entity.MealRecord_DevA;
import com.everybite.entity.User_DevA;
import com.everybite.responsitory.ExerciseRecordRepository_DevA;
import com.everybite.responsitory.MealRecordRepository_DevA;
import com.everybite.responsitory.UserRepository_DevA;
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
            throw new IllegalArgumentException("Meal record not found with id: " + recordId);
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
        newRecord.setCaloriesBurned(request.getCaloriesBurned());

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
        record.setCaloriesBurned(request.getCaloriesBurned());

        return exerciseRecordRepository.save(record);
    }

    @Transactional
    public void deleteExerciseRecord(Long recordId) {
        if (!exerciseRecordRepository.existsById(recordId)) {
            throw new IllegalArgumentException("Exercise record not found with id: " + recordId);
        }
        exerciseRecordRepository.deleteById(recordId); // 이 부분이 빠져 있었어요!
    }
} 