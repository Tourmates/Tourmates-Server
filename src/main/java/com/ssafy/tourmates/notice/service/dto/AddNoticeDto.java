package com.ssafy.tourmates.notice.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddNoticeDto {

    private String pin;
    private String title;
    private String content;

    @Builder
    public AddNoticeDto(String pin, String title, String content) {
        this.pin = pin;
        this.title = title;
        this.content = content;
    }
}
