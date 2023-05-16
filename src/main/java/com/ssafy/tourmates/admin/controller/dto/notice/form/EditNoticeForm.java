package com.ssafy.tourmates.admin.controller.dto.notice.form;

import lombok.Data;

@Data
public class EditNoticeForm {

    private Long noticeId;
    private String pin;
    private String title;
    private String content;
}
