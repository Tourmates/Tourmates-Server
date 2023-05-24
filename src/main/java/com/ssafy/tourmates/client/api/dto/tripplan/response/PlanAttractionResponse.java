package com.ssafy.tourmates.client.api.dto.tripplan.response;

import com.ssafy.tourmates.client.api.dto.tripplan.request.TripPlanRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlanAttractionResponse {

    private List<TripPlanRequest> plans;

    @Builder
    public PlanAttractionResponse(List<TripPlanRequest> plans){
        this.plans = plans;
    }

}