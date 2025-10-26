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
    // 데이터를 그래프나 시각화용 형태로 가공하는 클래스.

    private final AnalysisService analysisService;

    // Lombok 없이 직접 생성자 작성
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    // [그래프 1] 탄단지 비율 비교 (백분율 기준)
    @GetMapping("/macro-ratio")
    public MacroRatioDto getDailyMacroRatio(
            @RequestParam String date, // 분석 기준 날짜
            @RequestParam DietType dietType, // 식단 유형(ex. DIET, BULK_UP...)
            @RequestParam(required = false) Map<String, Double> customRatio // 사용자 커스텀 비율(선택적)
    ) {
        LocalDate localDate = LocalDate.parse(date); // 문자열을 LocalDate로 변환
        return analysisService.getDailyMacroRatio(localDate, dietType, customRatio);
    }

    // [그래프 2] 하루 칼로리 시각화 (아침/점심/저녁/간식 + 남은 칼로리)
    @GetMapping("/meal-calories")
    public MealCaloriesDto getDailyMealCalories(
            @RequestParam String date, // 기준 날짜
            @RequestParam double goal // 목표 칼로리
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return analysisService.getDailyMealCalories(localDate, goal);
    }

    // [그래프 3] 영양소 막대 그래프 (Kcal 기준)
    @GetMapping("/nutrient-bars")
    public List<NutrientBarDto> getDailyNutrientBars(
            @RequestParam String date, //기준 날짜
            @RequestParam double goal, // 목표 칼로리
            @RequestParam DietType dietType // 식단 유형
    ) {
        LocalDate localDate = LocalDate.parse(date);
        return analysisService.getDailyNutrientBars(localDate, goal, dietType);
        // 그래프를 그리기 위한 데이터 반환
    }

}
