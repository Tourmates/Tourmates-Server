package com.ssafy.tourmates.admin.trend.repository;

import com.ssafy.tourmates.admin.trend.Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrendRepository extends JpaRepository<Trend, Long> {

    @Query("select t from Trend t where t.attractionInfo.id=:contentId")
    Optional<Trend> findByAttractionInfoId(@Param("contentId") Integer contentId);
}
