package com.example.meal_tracker.service; // 본인 패키지 경로 확인

import com.example.meal_tracker.domain.User;
import com.example.meal_tracker.domain.UserGoal;
import com.example.meal_tracker.dto.CreateUserGoalRequest;
import com.example.meal_tracker.repository.UserGoalRepository;
import com.example.meal_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGoalService {

    private final UserGoalRepository userGoalRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserGoal createUserGoal(CreateUserGoalRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        Optional<UserGoal> existingGoal = userGoalRepository.findByUserId(request.getUserId());
        if (existingGoal.isPresent()) {
            throw new IllegalArgumentException("User already has a goal. (Use PUT to update)");
        }

        UserGoal newGoal = new UserGoal();
        newGoal.setUser(user);
        newGoal.setTargetWeight(request.getTargetWeight());
        newGoal.setTargetCalories(request.getTargetCalories());
        newGoal.setStartDate(request.getStartDate());
        newGoal.setEndDate(request.getEndDate());

        return userGoalRepository.save(newGoal);
    }

    @Transactional(readOnly = true)
    public UserGoal findUserGoalByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return userGoalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found for user id: " + userId));
    }

    @Transactional
    public UserGoal updateUserGoal(Long userId, CreateUserGoalRequest request) {
        UserGoal goal = userGoalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found for user id: " + userId));

        goal.setTargetWeight(request.getTargetWeight());
        goal.setTargetCalories(request.getTargetCalories());
        goal.setStartDate(request.getStartDate());
        goal.setEndDate(request.getEndDate());

        return userGoalRepository.save(goal);
    }

    // --- (새로 추가된 메소드) ---
    @Transactional
    public void deleteUserGoal(Long userId) {
        // 1. 유저 ID로 목표를 찾습니다.
        UserGoal goal = userGoalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found for user id: " + userId));

        // 2. 찾은 목표를 삭제합니다. (ID로 삭제해도 동일합니다)
        userGoalRepository.delete(goal);
        // 또는 userGoalRepository.deleteById(goal.getId());
    }
}