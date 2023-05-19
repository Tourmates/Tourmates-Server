package com.ssafy.tourmates.client.api.dto.board.response;

import lombok.Data;

@Data
public class BoardCommentResponse {

    private String content;

    public BoardCommentResponse(String content){
        this.content = content;
    }
}
