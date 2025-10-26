package com.everybite.dto;

import java.util.Map;

public class MacroRatioDto {

    private Map<String, Double> recommended;
    private Map<String, Double> actual;

    public MacroRatioDto() {}

    public MacroRatioDto(Map<String, Double> recommended, Map<String, Double> actual) {
        this.recommended = recommended;
        this.actual = actual;
    }

    public Map<String, Double> getRecommended() {
        return recommended;
    }

    public void setRecommended(Map<String, Double> recommended) {
        this.recommended = recommended;
    }

    public Map<String, Double> getActual() {
        return actual;
    }

    public void setActual(Map<String, Double> actual) {
        this.actual = actual;
    }
}
// 수정 완료.
