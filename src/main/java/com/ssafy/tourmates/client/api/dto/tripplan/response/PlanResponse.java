package com.ssafy.tourmates.client.api.dto.tripplan.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PlanResponse {

    private Long tripPlanId;
    private String title;
    private String nickname;
    private Integer hit;
    private String createdDate;

    public PlanResponse(Long tripPlanId, String title, String nickname, Integer hit, LocalDateTime createdDate) {
        this.tripPlanId = tripPlanId;
        this.title = title;
        this.nickname = nickname;
        this.hit = hit;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
