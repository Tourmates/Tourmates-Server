package com.ssafy.tourmates.client.api.dto.board.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DetailBoardResponse {

    private Long boardId;
    private String title;
    private String content;
    private int hit;
    private String createdDate;

    private String nickname;

    public DetailBoardResponse(Long boardId, String title, String content, int hit, LocalDateTime createdDate, String nickname) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.nickname = nickname;
    }
}
