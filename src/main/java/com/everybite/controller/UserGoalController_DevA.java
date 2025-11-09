package com.everybite.controller;

import com.everybite.dto.CreateUserGoalRequest_DevA;
import com.everybite.dto.UserGoalResponse_DevA;
import com.everybite.entity.UserGoal_DevA;
import com.everybite.service.UserGoalService_DevA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goals")
public class UserGoalController_DevA {

    private final UserGoalService_DevA userGoalService;

    // POST /api/goals (생성)
    @PostMapping
    public ResponseEntity<UserGoalResponse_DevA> createUserGoal(
            @RequestBody CreateUserGoalRequest_DevA request) {

        UserGoal_DevA savedGoal = userGoalService.createUserGoal(request);

        return ResponseEntity.ok(new UserGoalResponse_DevA(savedGoal));
    }

    // GET /api/goals/user/{userId} (조회)
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserGoalResponse_DevA> getUserGoalByUserId(
            @PathVariable("userId") Long userId) {

        UserGoal_DevA goal = userGoalService.findUserGoalByUserId(userId);

        return ResponseEntity.ok(new UserGoalResponse_DevA(goal));
    }

    // PUT /api/goals/user/{userId} (수정)
    @PutMapping("/user/{userId}")
    public ResponseEntity<UserGoalResponse_DevA> updateUserGoal(
            @PathVariable("userId") Long userId,
            @RequestBody CreateUserGoalRequest_DevA request) {

        UserGoal_DevA updatedGoal = userGoalService.updateUserGoal(userId, request);

        return ResponseEntity.ok(new UserGoalResponse_DevA(updatedGoal));
    }

    // DELETE /api/goals/user/{userId} (삭제)
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUserGoal(@PathVariable("userId") Long userId) {
        userGoalService.deleteUserGoal(userId);
        return ResponseEntity.ok().build();
    }
}