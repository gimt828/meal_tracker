package com.everybite.dto;

public class GoalAchievementDto {
    private int streakDays; // 연속 달성 일수
    private boolean achievedToday; // 오늘 목표 달성 여부 (t/f)
    private String streakIconUrl; // 달성 상태에 따라 표시할 아이콘 이미지 경로 (ex.png)

    public GoalAchievementDto() {
    }

    public GoalAchievementDto(int streakDays, boolean achievedToday, String streakIconUrl) {
        this.streakDays = streakDays;
        this.achievedToday = achievedToday;
        this.streakIconUrl = streakIconUrl;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(int streakDays) {
        this.streakDays = streakDays;
    }

    public boolean isAchievedToday() {
        return achievedToday;
    }

    public void setAchievedToday(boolean achievedToday) {
        this.achievedToday = achievedToday;
    }

    public String getStreakIconUrl() {
        return streakIconUrl;
    }

    public void setStreakIconUrl(String streakIconUrl) {
        this.streakIconUrl = streakIconUrl;
    }
}
