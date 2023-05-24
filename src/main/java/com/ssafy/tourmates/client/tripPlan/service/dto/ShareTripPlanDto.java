package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ShareTripPlanDto {

    private Long tripPlanId;
    private String targetId;

    @Builder
    public ShareTripPlanDto(Long tripPlanId, String targetId) {
        this.tripPlanId = tripPlanId;
        this.targetId = targetId;
    }
}
