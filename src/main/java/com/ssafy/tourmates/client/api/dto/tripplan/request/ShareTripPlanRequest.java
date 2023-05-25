package com.ssafy.tourmates.client.api.dto.tripplan.request;

import lombok.Data;

@Data
public class ShareTripPlanRequest {

    private Long tripPlanId;
    private String nickname;
}
