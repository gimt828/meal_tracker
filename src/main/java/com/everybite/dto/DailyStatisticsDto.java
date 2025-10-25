package com.everybite.dto;

import java.time.LocalDate; // 날짜 처리를 위한 Java Time API 클래스 import.

public class DailyStatisticsDto {
    // 하루 단위의 통계 데이터를 담는 DTO 클래스 정의.

    private LocalDate date;          // 통계 날짜
    private double totalCaloriesIn;  // 총 섭취 칼로리
    private double totalCaloriesOut; // 총 소모 칼로리
    private double netCalories;      // 순칼로리 (섭취 - 소모)
    private double totalCarbs;       // 총 탄수화물(g)
    private double totalProtein;     // 총 단백질(g)
    private double totalFat;         // 총 지방(g)
    private double goalCalories;     // 하루 목표 칼로리
    private double remainingCalories;// 남은 칼로리 (goal - 섭취)

    //  기본 생성자
    public DailyStatisticsDto() {}

    //  전체 필드 생성자
    public DailyStatisticsDto(LocalDate date, double totalCaloriesIn, double totalCaloriesOut, double netCalories,
                              double totalCarbs, double totalProtein, double totalFat,
                              double goalCalories, double remainingCalories) {
        this.date = date;
        this.totalCaloriesIn = totalCaloriesIn;
        this.totalCaloriesOut = totalCaloriesOut;
        this.netCalories = netCalories;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;
        this.goalCalories = goalCalories;
        this.remainingCalories = remainingCalories;
    }

    // Getter / Setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public double getGoalCalories() {
        return goalCalories;
    }

    public void setGoalCalories(double goalCalories) {
        this.goalCalories = goalCalories;
    }

    public double getRemainingCalories() {
        return remainingCalories;
    }

    public void setRemainingCalories(double remainingCalories) {
        this.remainingCalories = remainingCalories;
    }

    //  객체 상태를 문자열로 반환 (디버깅 / 로그용)
    @Override
    public String toString() {
        return "DailyStatisticsDto{" +
                "date=" + date +
                ", totalCaloriesIn=" + totalCaloriesIn +
                ", totalCaloriesOut=" + totalCaloriesOut +
                ", netCalories=" + netCalories +
                ", totalCarbs=" + totalCarbs +
                ", totalProtein=" + totalProtein +
                ", totalFat=" + totalFat +
                ", goalCalories=" + goalCalories +
                ", remainingCalories=" + remainingCalories +
                '}';
    }
}
