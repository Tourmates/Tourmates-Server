package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ShareTripPlanDto {

    private Long tripPlanId;
    private String friendId;

    @Builder
    public ShareTripPlanDto(Long tripPlanId, String friendId) {
        this.tripPlanId = tripPlanId;
        this.friendId = friendId;
    }
}
