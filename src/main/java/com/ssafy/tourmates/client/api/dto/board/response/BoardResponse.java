package com.ssafy.tourmates.client.api.dto.board.response;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BoardResponse {

    private Long boardId;
    private ContentType tag;
    private String title;
    private int hit;
    private String nickname;
    private String createdDate;

    public BoardResponse(Long boardId, ContentType tag, String title, int hit, String nickname, LocalDateTime createdDate) {
        this.boardId = boardId;
        this.tag = tag;
        this.title = title;
        this.hit = hit;
        this.nickname = nickname;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
