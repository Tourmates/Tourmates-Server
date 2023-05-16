package com.ssafy.tourmates.admin.api.dto.notice.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NoticeResponse {

    private Long noticeId;
    private Boolean pin;
    private String title;
    private String createdDate;

    public NoticeResponse(Long noticeId, String pin, String title, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.pin = pin.equals("1");
        this.title = title;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
    }
}
