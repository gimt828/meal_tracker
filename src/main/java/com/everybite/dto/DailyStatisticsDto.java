package com.everybite.dto;

import java.time.LocalDate;

public class DailyStatisticsDto {
	// // 하루 단위의 통계 데이터를 담는 DTO 클래스 정의.

    private LocalDate date;				 // 통계 날짜
    private double total_calories_in;	 // 총 섭취 칼로리
    private double total_calories_out;   // 총 소모 칼로리
    private double net_calories;		 // 순칼로리 (섭취 - 소모)
    private double total_carbs;			 // 총 탄수화물(g)
    private double total_protein;		 // 총 단백질(g)
    private double total_fat;			 // 총 지방(g)
    private double goal_calories;		 // 하루 목표 칼로리
    private double remaining_calories;   // 남은 칼로리 (goal - 섭취)

    // 기본생성자
    public DailyStatisticsDto() {}

    
    // Getter / Setter
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

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

    public double getRemaining_calories() { return remaining_calories; }
    public void setRemaining_calories(double remaining_calories) { this.remaining_calories = remaining_calories; }
}
