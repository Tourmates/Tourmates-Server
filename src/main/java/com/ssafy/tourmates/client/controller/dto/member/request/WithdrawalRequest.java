package com.ssafy.tourmates.client.controller.dto.member.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WithdrawalRequest {

    @NotBlank
    private String loginPw;
}
