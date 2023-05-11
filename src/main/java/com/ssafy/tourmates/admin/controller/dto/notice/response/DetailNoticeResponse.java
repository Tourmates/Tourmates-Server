package com.ssafy.tourmates.admin.controller.dto.notice.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailNoticeResponse {
    private Long noticeId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
}

