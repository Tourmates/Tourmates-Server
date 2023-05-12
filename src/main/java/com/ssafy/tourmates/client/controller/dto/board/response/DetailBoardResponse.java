package com.ssafy.tourmates.client.controller.dto.board.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailBoardResponse {

    private Long boardId;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime createdDate;

    private String nickname;
}
