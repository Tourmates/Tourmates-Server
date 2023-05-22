package com.ssafy.tourmates.client.api.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditMyPersonalRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotBlank
    private String emailId;

    @NotBlank
    private String emailDomain;

    @NotBlank
    private String startPhoneNumber;

    @NotBlank
    private String middlePhoneNumber;

    @NotBlank
    private String endPhoneNumber;
}
