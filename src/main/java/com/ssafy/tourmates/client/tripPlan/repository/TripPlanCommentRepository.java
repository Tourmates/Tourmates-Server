package com.ssafy.tourmates.client.tripPlan.repository;

import com.ssafy.tourmates.client.tripPlan.TripPlanComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripPlanCommentRepository extends JpaRepository<TripPlanComment, Long> {

    List<TripPlanComment> findAllByTripPlanId(@Param("tripPlanId") Long tripPlanId);
}
