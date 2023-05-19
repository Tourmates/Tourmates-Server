package com.ssafy.tourmates.client.api.dto.board.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardCommentResponse {

    private String nickName;
    private String content;
    private LocalDateTime createdTime;

    public BoardCommentResponse(String nickName, String content, LocalDateTime createdTime){
        this.nickName = nickName;
        this.content = content;
        this.createdTime = createdTime;
    }
}
