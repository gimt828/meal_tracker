package com.everybite.dto;

public class MonthlyStatisticsDto {
	// 월간 통계 데이터 관련
	
    private int year;					// 연도 (ex. 2025)
    private int month;					// 월 (ex. 10)
    private double total_calories_in;	// 총 섭취 칼로리
    private double total_calories_out;  // 총 소모 칼로리
    private double net_calories;		// 총 순칼로리 ( 섭취 - 소모)
    private double total_carbs;			// 탄수화물 총합
    private double total_protein;		// 단백질 총합
    private double total_fat;			// 지방 총합
    private double goal_calories;		// 하루 목표 칼로리
    private double achievement_rate;	// 달성률(%)

    // 기본 생성자
    public MonthlyStatisticsDto() {}

    // Getter / Setter
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public double getTotal_calories_in() { return total_calories_in; }
    public void setTotal_calories_in(double total_calories_in) { this.total_calories_in = total_calories_in; }

    public double getTotal_calories_out() { return total_calories_out; }
    public void setTotal_calories_out(double total_calories_out) { this.total_calories_out = total_calories_out; }

    public double getNet_calories() { return net_calories; }
    public void setNet_calories(double net_calories) { this.net_calories = net_calories; }

    public double getTotal_carbs() { return total_carbs; }
    public void setTotal_carbs(double total_carbs) { this.total_carbs = total_carbs; }

    public double getTotal_protein() { return total_protein; }
    public void setTotal_protein(double total_protein) { this.total_protein = total_protein; }

    public double getTotal_fat() { return total_fat; }
    public void setTotal_fat(double total_fat) { this.total_fat = total_fat; }

    public double getGoal_calories() { return goal_calories; }
    public void setGoal_calories(double goal_calories) { this.goal_calories = goal_calories; }

    public double getAchievement_rate() { return achievement_rate; }
    public void setAchievement_rate(double achievement_rate) { this.achievement_rate = achievement_rate; }
    

}
