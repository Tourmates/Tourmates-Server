package com.ssafy.tourmates.admin.trend.service;

import com.ssafy.tourmates.admin.api.dto.trend.reseponse.TopTrendResponse;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrendQueryService {

    TopTrendResponse getTotalTrend();
}
