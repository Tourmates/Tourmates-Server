package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TripPlanCommentService {

    Long registerTripPlanComment(String loginId, Long tripPlanId, AddTripPlanCommentDto dto);
}
