package com.everybite.dto;

import java.util.Map;

public class MacroRatioDto {
    
    // 추천 비율 (식단 타입 or 커스텀 기준)
    private Map<String, Double> recommended;
    // 실제 섭취 비율(해당 날짜 식사 기록 기준)
    private Map<String, Double> actual;

    public MacroRatioDto() {}

    // 전체 필드 초기화 생성자
    public MacroRatioDto(Map<String, Double> recommended, Map<String, Double> actual) {
        this.recommended = recommended;
        this.actual = actual;
    }

    // Getter / Setter
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
