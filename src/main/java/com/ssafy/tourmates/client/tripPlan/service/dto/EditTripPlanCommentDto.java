package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditTripPlanCommentDto {

    private String content;

    @Builder
    public EditTripPlanCommentDto(String content) {
        this.content = content;
    }
}
