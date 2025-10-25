package com.everybite.dto;

public class MonthlyStatisticsDto {

    private int year;               // 연도 (예: 2025)
    private int month;              // 월 (예: 10)
    private double totalCaloriesIn; // 총 섭취 칼로리
    private double totalCaloriesOut;// 총 소모 칼로리
    private double netCalories;     // 총 순칼로리 (섭취 - 소모)
    private double totalCarbs;      // 탄수화물 총합
    private double totalProtein;    // 단백질 총합
    private double totalFat;        // 지방 총합
    private int goalCalories;       // 하루 목표 칼로리
    private double achievementRate; // 달성률 (%)

    // 기본 생성자
    public MonthlyStatisticsDto() {}

    // 전체 필드 생성자
    public MonthlyStatisticsDto(int year, int month, double totalCaloriesIn, double totalCaloriesOut,
                                double netCalories, double totalCarbs, double totalProtein,
                                double totalFat, int goalCalories, double achievementRate) {
        this.year = year;
        this.month = month;
        this.totalCaloriesIn = totalCaloriesIn;
        this.totalCaloriesOut = totalCaloriesOut;
        this.netCalories = netCalories;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;
        this.goalCalories = goalCalories;
        this.achievementRate = achievementRate;
    }

    // Getter / Setter
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getTotalCaloriesIn() {
        return totalCaloriesIn;
    }

    public void setTotalCaloriesIn(double totalCaloriesIn) {
        this.totalCaloriesIn = totalCaloriesIn;
    }

    public double getTotalCaloriesOut() {
        return totalCaloriesOut;
    }

    public void setTotalCaloriesOut(double totalCaloriesOut) {
        this.totalCaloriesOut = totalCaloriesOut;
    }

    public double getNetCalories() {
        return netCalories;
    }

    public void setNetCalories(double netCalories) {
        this.netCalories = netCalories;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public int getGoalCalories() {
        return goalCalories;
    }

    public void setGoalCalories(int goalCalories) {
        this.goalCalories = goalCalories;
    }

    public double getAchievementRate() {
        return achievementRate;
    }

    public void setAchievementRate(double achievementRate) {
        this.achievementRate = achievementRate;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "MonthlyStatisticsDto{" +
                "year=" + year +
                ", month=" + month +
                ", totalCaloriesIn=" + totalCaloriesIn +
                ", totalCaloriesOut=" + totalCaloriesOut +
                ", netCalories=" + netCalories +
                ", totalCarbs=" + totalCarbs +
                ", totalProtein=" + totalProtein +
                ", totalFat=" + totalFat +
                ", goalCalories=" + goalCalories +
                ", achievementRate=" + achievementRate +
                '}';
    }
}
