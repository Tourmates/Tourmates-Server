package com.ssafy.tourmates.client.tripPlan.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PlanSearchCondition {

    private String title;
    private String attractionTitle;
    private String nickname;

    @Builder
    public PlanSearchCondition(String title, String attractionTitle, String nickname) {
        this.title = title;
        this.attractionTitle = attractionTitle;
        this.nickname = nickname;
    }
}
