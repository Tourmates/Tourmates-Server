package com.ssafy.tourmates.admin.controller.dto.notice.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NoticeResponse {

    private Long noticeId;
    private String title;
    private String createdDate;

    public NoticeResponse(Long noticeId, String title, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
    }
}
