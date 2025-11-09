package com.everybite.service;

import com.everybite.dto.CreateUserGoalRequest_DevA;
import com.everybite.entity.UserGoal_DevA;
import com.everybite.entity.User_DevA;
import com.everybite.responsitory.UserGoalRepository_DevA;
import com.everybite.responsitory.UserRepository_DevA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGoalService_DevA {

    private final UserGoalRepository_DevA userGoalRepository;
    private final UserRepository_DevA userRepository;

    @Transactional
    public UserGoal_DevA createUserGoal(CreateUserGoalRequest_DevA request) {
        User_DevA user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        Optional<UserGoal_DevA> existingGoal = userGoalRepository.findByUserId(request.getUserId());
        if (existingGoal.isPresent()) {
            throw new IllegalArgumentException("User already has a goal. (Use PUT to update)");
        }

        UserGoal_DevA newGoal = new UserGoal_DevA();
        newGoal.setUser(user);
        newGoal.setTargetWeight(request.getTargetWeight());
        newGoal.setTargetCalories(request.getTargetCalories());
        newGoal.setStartDate(request.getStartDate());
        newGoal.setEndDate(request.getEndDate());

        return userGoalRepository.save(newGoal);
    }

    @Transactional(readOnly = true)
    public UserGoal_DevA findUserGoalByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return userGoalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found for user id: " + userId));
    }

    @Transactional
    public UserGoal_DevA updateUserGoal(Long userId, CreateUserGoalRequest_DevA request) {
        UserGoal_DevA goal = userGoalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found for user id: " + userId));

        goal.setTargetWeight(request.getTargetWeight());
        goal.setTargetCalories(request.getTargetCalories());
        goal.setStartDate(request.getStartDate());
        goal.setEndDate(request.getEndDate());

        return userGoalRepository.save(goal);
    }

    @Transactional
    public void deleteUserGoal(Long userId) {
        UserGoal_DevA goal = userGoalRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found for user id: " + userId));

        userGoalRepository.delete(goal);
    }
}