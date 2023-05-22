package com.ssafy.tourmates.client.tripPlan.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AddTripPlanDto {

    private String title;
    private List<Integer> contentIds;

    @Builder
    public AddTripPlanDto(String title, List<Integer> contentIds) {
        this.title = title;
        this.contentIds = contentIds;
    }
}
