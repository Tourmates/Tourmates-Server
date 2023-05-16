package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.tripPlan.service.dto.AddDetailTripPlanDto;

import java.util.List;

public interface DetailTripPlanService {
    List<Integer> registerDetailTripPlan(Long tripPlanId, AddDetailTripPlanDto dto);

    void removeDetailTripPlan(Long detailTripPlanId);
}
