package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.ShareTripPlanDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TripPlanService {

    Long registerTripPlan(String loginId, AddTripPlanDto dto);

    Long editTripPlan(Long tripPlanId, EditTripPlanDto dto);

    Long increaseHit(Long tripPlanId);

    Long removeTripPlan(Long tripPlanId);

    Long shareTripPlan(ShareTripPlanDto dto);
}
