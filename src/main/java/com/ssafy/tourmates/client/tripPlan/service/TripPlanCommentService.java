package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TripPlanCommentService {

    Long registerTripPlanComment(String loginId, Long tripPlanId, AddTripPlanCommentDto dto);

    Long editTripPlanComment(Long tripPlanId, Long tripPlanCommentId, EditTripPlanCommentDto dto);

    void removeTripPlanComment(Long tripPlanCommentId);
}
