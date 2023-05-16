package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AddDetailTripPlanDto {

    private List<Long> contendIds;

    @Builder
    public AddDetailTripPlanDto(List<Long> contendIds) {
        this.contendIds = contendIds;
    }
}
