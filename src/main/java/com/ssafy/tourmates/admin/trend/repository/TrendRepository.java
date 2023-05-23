package com.ssafy.tourmates.admin.trend.repository;

import com.ssafy.tourmates.admin.trend.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendRepository extends JpaRepository<Trend, Long> {
}
