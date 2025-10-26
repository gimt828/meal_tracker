package com.everybite.controller;

import com.everybite.dto.GoalAchievementDto;
import com.everybite.service.GoalAchievementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
public class GoalAchievementController {
  // 목표 달성 상태와 관련된 API 담당하는 클래스.

    private final GoalAchievementService goalAchievementService;

    public GoalAchievementController(GoalAchievementService goalAchievementService) {
        this.goalAchievementService = goalAchievementService;
    }

    @GetMapping("/goal-streak")
    public ResponseEntity<GoalAchievementDto> getGoalStreak() {
        GoalAchievementDto result = goalAchievementService.calculateStreak();
        return ResponseEntity.ok(result);
    }
}
