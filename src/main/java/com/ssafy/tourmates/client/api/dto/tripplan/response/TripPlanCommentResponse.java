package com.ssafy.tourmates.client.api.dto.tripplan.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripPlanCommentResponse {

    private String nickName;
    private String content;
    private LocalDateTime createdTime;

    public TripPlanCommentResponse(String nickName, String content, LocalDateTime createdTime){
        this.nickName = nickName;
        this.content = content;
        this.createdTime = createdTime;
    }
}
