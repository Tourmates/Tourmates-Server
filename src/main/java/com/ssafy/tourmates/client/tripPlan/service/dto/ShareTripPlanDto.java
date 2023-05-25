package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ShareTripPlanDto {

    private Long tripPlanId;
    private String nickname;

    @Builder
    public ShareTripPlanDto(Long tripPlanId, String nickname) {
        this.tripPlanId = tripPlanId;
        this.nickname = nickname;
    }
}
