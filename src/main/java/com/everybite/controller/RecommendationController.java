package com.everybite.controller;

import com.everybite.dto.RecommendedMealDto;
import com.everybite.service.RecommendationService;
import com.everybite.dto.RecommendationResponseDto;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * 리롤 버튼 클릭 시 새 추천 리스트 반환
     * @param mealType (아침, 점심, 저녁)
     * 간식은 RecmmendationService.java에서 제어함.
     */
    @GetMapping
    public RecommendationResponseDto recommendMeals(@RequestParam String mealType) {
        List<RecommendedMealDto> result = recommendationService.recommendMealsWithReroll(mealType);
        return new RecommendationResponseDto(mealType, result);
    }

}
