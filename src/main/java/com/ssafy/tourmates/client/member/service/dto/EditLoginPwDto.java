package com.ssafy.tourmates.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditLoginPwDto {

    private String currentLoginPw;
    private String newLoginPw;

    @Builder
    public EditLoginPwDto(String currentLoginPw, String newLoginPw) {
        this.currentLoginPw = currentLoginPw;
        this.newLoginPw = newLoginPw;
    }
}
