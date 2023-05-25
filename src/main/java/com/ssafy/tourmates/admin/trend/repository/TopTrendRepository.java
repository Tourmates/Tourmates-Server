package com.ssafy.tourmates.admin.trend.repository;

import com.ssafy.tourmates.admin.trend.TopTrend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopTrendRepository extends JpaRepository<TopTrend, Long> {
}
