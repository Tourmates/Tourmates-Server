package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.api.dto.tripplan.response.TripPlanCommentResponse;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanCommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TripPlanCommentService {

    Long registerTripPlanComment(String loginId, Long tripPlanId, AddTripPlanCommentDto dto);

    Long editTripPlanComment(Long tripPlanId, Long tripPlanCommentId, EditTripPlanCommentDto dto);

    Long removeTripPlanComment(Long tripPlanCommentId);

    List<TripPlanCommentResponse> searchAll(Long tripPlanId);
}
