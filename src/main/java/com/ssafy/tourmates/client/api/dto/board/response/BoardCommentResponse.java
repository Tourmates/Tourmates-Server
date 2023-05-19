package com.ssafy.tourmates.client.api.dto.board.response;

import lombok.Data;

@Data
public class BoardCommentResponse {

    private String content;
    private String nickName;

    public BoardCommentResponse(String nickName, String content){
        this.nickName = nickName;
        this.content = content;
    }
}
