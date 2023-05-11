package com.ssafy.tourmates.admin.controller.dto.notice.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddNoticeRequest {

    @NotBlank
    private String pin;
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    private String content;
}
