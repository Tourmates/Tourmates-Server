package com.ssafy.tourmates.admin.controller.dto.notice.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResponse {

    private Long noticeId;
    private String title;
    private LocalDateTime createdDate;
}
