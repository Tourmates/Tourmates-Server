package com.ssafy.tourmates.admin.controller.dto.notice.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class EditNoticeResponse {

    private Long noticeId;
    private String title;
    private String content;
    private Boolean pin;
    private String createdDate;

    public EditNoticeResponse(Long noticeId, String title, String content, String pin, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.pin = pin.equals("1");
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
