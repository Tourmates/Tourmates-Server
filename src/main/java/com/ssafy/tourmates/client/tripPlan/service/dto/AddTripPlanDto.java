package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddTripPlanDto {

    private String title;

    @Builder
    public AddTripPlanDto(String title) {
        this.title = title;
    }

}
