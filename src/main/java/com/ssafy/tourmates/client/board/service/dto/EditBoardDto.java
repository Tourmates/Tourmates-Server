package com.ssafy.tourmates.client.board.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditBoardDto {

    private String title;
    private String content;

    @Builder
    public EditBoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
