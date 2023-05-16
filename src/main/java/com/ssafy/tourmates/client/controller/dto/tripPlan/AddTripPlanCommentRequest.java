package com.ssafy.tourmates.client.controller.dto.tripPlan;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddTripPlanCommentRequest {

    @NotBlank
    private String comment;
}
