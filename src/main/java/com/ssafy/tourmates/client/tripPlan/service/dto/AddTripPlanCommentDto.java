package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddTripPlanCommentDto {

    private String content;

    @Builder
    public AddTripPlanCommentDto(String content) {
        this.content = content;
    }
}
