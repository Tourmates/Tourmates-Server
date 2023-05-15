package com.ssafy.tourmates.client.board.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditBoardCommentDto {

    private String content;

    @Builder
    public EditBoardCommentDto(String content) {
        this.content = content;
    }
}
