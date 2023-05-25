package com.ssafy.tourmates.config;

import com.ssafy.tourmates.admin.trend.service.TrendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks {

    private final TrendService trendService;

    @Scheduled(cron = "0 0 0/1 * * *")
//    @Scheduled(cron = "0 * * * * *")
    public void topTrends() {
        log.debug("register topTrends start");
        trendService.registerTopTrend();
        log.debug("register topTrends end");
    }
}
