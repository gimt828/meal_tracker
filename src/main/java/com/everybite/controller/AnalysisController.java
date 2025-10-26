package com.everybite.controller;

import com.everybite.dto.MacroRatioDto;
import com.everybite.dto.MealCaloriesDto;
import com.everybite.entity.DietType;
import com.everybite.service.AnalysisService;
import com.everybite.dto.NutrientBarDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    // Lombok 없이 직접 생성자 작성
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    // [그래프 ①] 탄단지 비율 비교
    @GetMapping("/macro-ratio")
    public MacroRatioDto getDailyMacroRatio(
            @RequestParam String date,
            @RequestParam DietType dietType,
            @RequestParam(required = false) Map<String, Double> customRatio
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return analysisService.getDailyMacroRatio(localDate, dietType, customRatio);
    }

    // [그래프 ②] 하루 칼로리 시각화 (아침/점심/저녁/간식 + 남은 칼로리)
    @GetMapping("/meal-calories")
    public MealCaloriesDto getDailyMealCalories(
            @RequestParam String date,
            @RequestParam double goal
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return analysisService.getDailyMealCalories(localDate, goal);
    }
    
    @GetMapping("/nutrient-bars")
    public List<NutrientBarDto> getDailyNutrientBars(
            @RequestParam String date,
            @RequestParam double goal,
            @RequestParam DietType dietType
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return analysisService.getDailyNutrientBars(localDate, goal, dietType);
    }

}
