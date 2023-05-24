package com.ssafy.tourmates.admin.trend.service.impl;

import com.ssafy.tourmates.admin.api.dto.trend.reseponse.TopTrendResponse;
import com.ssafy.tourmates.admin.api.dto.trend.reseponse.TrendResponse;
import com.ssafy.tourmates.admin.trend.repository.TrendQueryRepository;
import com.ssafy.tourmates.admin.trend.service.TrendQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrendQueryServiceImpl implements TrendQueryService {

    private final TrendQueryRepository trendQueryRepository;

    public TopTrendResponse getTotalTrend() {
        List<TrendResponse> teenage = trendQueryRepository.searchTeenageTopTrend();
        List<TrendResponse> twenty = trendQueryRepository.searchTwentyTopTrend();
        List<TrendResponse> thirty = trendQueryRepository.searchThirtyTopTrend();
        List<TrendResponse> male = trendQueryRepository.searchMaleTopTrend();
        List<TrendResponse> female = trendQueryRepository.searchFemaleTopTrend();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:00"));
        return new TopTrendResponse(time, teenage, twenty, thirty, male, female);
    }
}
