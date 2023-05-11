package com.ssafy.tourmates.client.board.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddBoardDto {

    private String title;
    private String content;

    @Builder
    public AddBoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
