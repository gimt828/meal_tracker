package com.everybite.service;

import com.everybite.dto.GoalAchievementDto;
import com.everybite.entity.MealIntakeRecord;
import com.everybite.repository.MealIntakeRecordRepository;
import com.everybite.repository.UserGoalRepository;
import com.everybite.entity.UserGoal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoalAchievementService {

    private final MealIntakeRecordRepository mealIntakeRecordRepository;
    private final UserGoalRepository userGoalRepository;

    public GoalAchievementService(MealIntakeRecordRepository mealIntakeRecordRepository,
                                  UserGoalRepository userGoalRepository) {
        this.mealIntakeRecordRepository = mealIntakeRecordRepository;
        this.userGoalRepository = userGoalRepository;
    }

    public GoalAchievementDto calculateStreak() {
        // 1. 목표 칼로리 불러오기
    	UserGoal userGoal = userGoalRepository.findByUserId(1L); // 단일 사용자 구조
    	double goalCalories = userGoal != null ? userGoal.getTarget_calories() : 2000.0; // null이면 기본값


        // 2. 최근 30일 섭취 기록 조회
        List<MealIntakeRecord> recentRecords =
                mealIntakeRecordRepository.findByDateBetween(LocalDate.now().minusDays(29), LocalDate.now());

        // 3️. 날짜별 총 섭취 칼로리 합산
        Map<LocalDate, Double> dailyCalories = recentRecords.stream()
                .collect(Collectors.groupingBy(
                        MealIntakeRecord::getDate,
                        Collectors.summingDouble(MealIntakeRecord::getCalories)
                ));

        // 4. 연속 달성 계산
        int streak = 0;
        boolean achievedToday = false;

      // 기록이 존재하고 목표 이하일 경우 streak 증가
        for (int i = 0; i < 30; i++) {
            LocalDate date = LocalDate.now().minusDays(i);
            Double totalCalories = dailyCalories.getOrDefault(date, 0.0);

            if (totalCalories <= goalCalories && totalCalories > 0) {
                streak++;
                if (i == 0) achievedToday = true; // 오늘 달성 여부 표시
            } else {
                // 기록이 없거나 목표 초과 시 streak 종료
                break;
            }
        }

        // 5. 아이콘 선택
        String iconUrl = getStreakIconUrl(streak);

        // 6. DTO 구성
        GoalAchievementDto dto = new GoalAchievementDto();
        dto.setStreakDays(streak);
        dto.setAchievedToday(achievedToday);
        dto.setStreakIconUrl(iconUrl);

        return dto;
    }

  private String getStreakIconUrl(int streak) {
        if (streak >= 14) return "🙂"; // 14일 이상
        if (streak >= 7) return "😃";  // 7~13일
        if (streak >= 3) return "😆";  // 3~6일
        return "?";					   // 0~2일: 기본 (미정)
    }
}
