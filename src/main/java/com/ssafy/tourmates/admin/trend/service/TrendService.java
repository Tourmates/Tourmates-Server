package com.ssafy.tourmates.admin.trend.service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
public interface TrendService {

    Long registerTrend(Integer contentId);

    Long increaseTrend(String loginId, Integer contentId);

    void registerTopTrend();
}
