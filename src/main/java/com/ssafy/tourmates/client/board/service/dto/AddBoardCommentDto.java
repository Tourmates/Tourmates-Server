package com.ssafy.tourmates.client.board.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddBoardCommentDto {

    private String content;

    @Builder
    public AddBoardCommentDto(String content) {
        this.content = content;
    }
}
