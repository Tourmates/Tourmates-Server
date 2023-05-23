package com.ssafy.tourmates.client.api.dto.tripplan.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class DetailPlanResponse {

    private Long tripPlanId;
    private String title;
    private String nickname;
    private String createdDate;
    private List<DetailPlanDataResponse> plans;

    public DetailPlanResponse(Long tripPlanId, String title, String nickname, LocalDateTime createdDate) {
        this.tripPlanId = tripPlanId;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm"));
    }
}
