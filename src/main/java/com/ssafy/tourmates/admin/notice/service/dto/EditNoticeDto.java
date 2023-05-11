package com.ssafy.tourmates.admin.notice.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditNoticeDto {

    private String pin;
    private String title;
    private String content;

    @Builder
    public EditNoticeDto(String pin, String title, String content) {
        this.pin = pin;
        this.title = title;
        this.content = content;
    }
}
