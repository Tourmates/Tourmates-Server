package com.ssafy.tourmates.client.controller.dto.board.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponse {

    private Long boardId;
    private String title;
    private int hit;
    private LocalDateTime createdDate;
}
