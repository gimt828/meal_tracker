package com.everybite.dto;

public class ChartDateDto {
    private String label; // 그래프에서 표시될 이름 (ex. 아점저간)
    private double value; // 해당 항목의 수치값 (ex. Kcal, g 등)

    public ChartDateDto() {}

    public ChartDateDto(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
