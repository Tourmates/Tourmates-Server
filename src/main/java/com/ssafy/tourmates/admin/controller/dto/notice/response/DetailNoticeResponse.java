package com.ssafy.tourmates.admin.controller.dto.notice.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DetailNoticeResponse {

    private Long noticeId;
    private String title;
    private String content;
    private String createdDate;

    public DetailNoticeResponse(Long noticeId, String title, String content, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}

