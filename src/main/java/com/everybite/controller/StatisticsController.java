package com.everybite.controller;

import com.everybite.dto.DailyStatisticsDto;
import com.everybite.dto.MonthlyStatisticsDto;
import com.everybite.service.StatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    //  Lombok @RequiredArgsConstructor 제거 → 수동 생성자 작성
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     *  일간 통계 API
     * GET /api/statistics/daily?date=2025-10-18&userId=1
     */
    @GetMapping("/daily")
    public ResponseEntity<DailyStatisticsDto> getDailyStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Long userId
    ) {
        DailyStatisticsDto stats = statisticsService.getDailyStatistics(date, userId);
        return ResponseEntity.ok(stats);
    }

    /**
     *  월간 통계 API
     * GET /api/statistics/monthly?year=2025&month=10&userId=1
     */
    @GetMapping("/monthly")
    public ResponseEntity<MonthlyStatisticsDto> getMonthlyStatistics(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam Long userId
    ) {
        MonthlyStatisticsDto stats = statisticsService.getMonthlyStatistics(year, month, userId);
        return ResponseEntity.ok(stats);
    }
}
