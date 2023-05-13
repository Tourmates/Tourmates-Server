package com.ssafy.tourmates.client.controller.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPw;
}
