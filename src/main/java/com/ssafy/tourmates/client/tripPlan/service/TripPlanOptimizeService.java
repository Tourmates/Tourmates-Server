package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanAttractionResponse;
import com.ssafy.tourmates.client.tripPlan.service.dto.PlanAttractionDto;

import java.util.List;


public interface TripPlanOptimizeService {
    List<PlanAttractionResponse> optimize(PlanAttractionDto dto);

}
