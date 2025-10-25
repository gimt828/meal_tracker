package com.everybite.controller;

import com.everybite.dto.DailyStatisticsDto;
import com.everybite.dto.MonthlyStatisticsDto;
import com.everybite.service.StatisticsService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController // JSON 형태로 응답을 반환하는 REST API 컨트롤러임을 명시.
@RequestMapping("/api/statistics") // 해당 컨트롤러의 기본 URI 경로 지정.
    // 이 클래스의 모든 요청 경로 앞에는 "/api/statistics"가 붙음.
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
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, // 쿼리스트랑(?date=...)을 LocalDate 타입으로 자동 변환.
            @RequestParam Long userId // {?userID=...) 파라미터를 Long 타입으로 받음.
    ) {
        DailyStatisticsDto stats = statisticsService.getDailyStatistics(date, userId); // 하루 통계 데이터 조회.
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
