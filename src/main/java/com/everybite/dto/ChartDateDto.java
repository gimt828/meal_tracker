package com.everybite.dto;

public class ChartDataDto {
    private String label;
    private double value;

    public ChartDataDto() {}

    public ChartDataDto(String label, double value) {
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
