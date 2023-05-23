package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ShareTripPlanDto {

    private String friendId;

    @Builder
    public ShareTripPlanDto(String friendId) {
        this.friendId = friendId;
    }
}
