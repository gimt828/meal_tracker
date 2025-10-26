package com.everybite.dto;

public class NutrientBarDto {
    private String label;   // 탄수화물 / 단백질 / 지방
    private double consumed; // 실제 섭취 kcal
    private double goal;     // 목표 kcal

    public NutrientBarDto() {}

    public NutrientBarDto(String label, double consumed, double goal) {
        this.label = label;
        this.consumed = consumed;
        this.goal = goal;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getConsumed() {
        return consumed;
    }

    public void setConsumed(double consumed) {
        this.consumed = consumed;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }
}
