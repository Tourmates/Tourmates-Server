package com.ssafy.tourmates.client.api.dto.tripplan.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlanAttractionRequest {

    private List<TripPlanRequest> plans;

    @Builder
    public PlanAttractionRequest(List<TripPlanRequest> plans){
        this.plans = plans;
    }

}

