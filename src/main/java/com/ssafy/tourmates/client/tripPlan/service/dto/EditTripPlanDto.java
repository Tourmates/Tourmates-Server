package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditTripPlanDto {

    private String title;

    @Builder
    public EditTripPlanDto(String content){
        this.title = title;
    }
}
