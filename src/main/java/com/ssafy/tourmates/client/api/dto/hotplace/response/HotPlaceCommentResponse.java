package com.ssafy.tourmates.client.api.dto.hotplace.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotPlaceCommentResponse {

    private String nickName;
    private String content;
    private LocalDateTime createdTime;

    public HotPlaceCommentResponse(String nickName, String content, LocalDateTime createdTime){
        this.nickName = nickName;
        this.content = content;
        this.createdTime = createdTime;
    }
}
