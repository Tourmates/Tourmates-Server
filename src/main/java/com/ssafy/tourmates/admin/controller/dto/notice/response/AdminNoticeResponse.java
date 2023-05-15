package com.ssafy.tourmates.admin.controller.dto.notice.response;

import com.ssafy.tourmates.client.member.Active;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.ssafy.tourmates.client.member.Active.*;

@Data
public class AdminNoticeResponse {

    private Long noticeId;
    private String title;
    private String writer;
    private String createdDate;
    private Boolean active;

    public AdminNoticeResponse(Long noticeId, String title, String writer, LocalDateTime createdDate, Active active) {
        this.noticeId = noticeId;
        this.title = title;
        this.writer = writer;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.active = active == ACTIVE;
    }
}
