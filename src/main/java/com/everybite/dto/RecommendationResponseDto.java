package com.everybite.dto;

import java.util.List;

public class RecommendationResponseDto {

    private String mealType;
    private int count;
    private List<RecommendedMealDto> recommendations;
    private String message; // 상태 메시지 (optional)

    public RecommendationResponseDto() {}

    public RecommendationResponseDto(String mealType, List<RecommendedMealDto> recommendations) {
        this.mealType = mealType;
        this.recommendations = recommendations;
        this.count = recommendations.size();
        this.message = "추천 결과가 성공적으로 생성되었습니다.";
    }

    // Getter / Setter
    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public List<RecommendedMealDto> getRecommendations() { return recommendations; }
    public void setRecommendations(List<RecommendedMealDto> recommendations) { this.recommendations = recommendations; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
