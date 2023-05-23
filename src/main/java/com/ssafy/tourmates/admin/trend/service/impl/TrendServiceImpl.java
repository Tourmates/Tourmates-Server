package com.ssafy.tourmates.admin.trend.service.impl;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.trend.Trend;
import com.ssafy.tourmates.admin.trend.repository.TrendRepository;
import com.ssafy.tourmates.admin.trend.service.TrendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService {

    private final TrendRepository trendRepository;

    @Override
    public Long registerTrend(Integer contentId) {
        Trend trend = Trend.builder()
                .attractionInfo(AttractionInfo.builder().id(contentId).build())
                .build();
        Trend savedTrend = trendRepository.save(trend);
        return savedTrend.getId();
    }

}
