package com.ssafy.tourmates.admin.trend.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrendService {

    Long registerTrend(Integer contentId);
}
