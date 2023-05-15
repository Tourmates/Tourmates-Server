package com.ssafy.tourmates.client.controller.dto.tripPlan;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditTripPlanRequest {

    @NotBlank
    @Size(max = 50)
    private String title;
}
