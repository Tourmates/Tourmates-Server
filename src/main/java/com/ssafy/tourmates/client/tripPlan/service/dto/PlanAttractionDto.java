package com.ssafy.tourmates.client.tripPlan.service.dto;

import com.ssafy.tourmates.client.api.dto.tripplan.request.TripPlanRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PlanAttractionDto {

    private List<TripPlanRequest> plans;

    @Builder
    public PlanAttractionDto(List<TripPlanRequest> plans){
        this.plans = plans;
    }

}
