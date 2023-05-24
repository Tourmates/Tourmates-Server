package com.ssafy.tourmates.admin.api;

import com.ssafy.tourmates.admin.api.dto.trend.reseponse.TopTrendResponse;
import com.ssafy.tourmates.admin.trend.service.TrendQueryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/trends")
public class TrendApiController {

    private final TrendQueryService trendQueryService;

    @GetMapping("/total")
    public Result<?> totalTrendTop10() {
        TopTrendResponse response = trendQueryService.getTotalTrend();
        return new Result<>(response);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
