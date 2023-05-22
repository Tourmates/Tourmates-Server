package com.ssafy.tourmates.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditMyPersonalDto {

    private String username;
    private String nickname;
    private String emailId;
    private String emailDomain;
    private String startPhoneNumber;
    private String middlePhoneNumber;
    private String endPhoneNumber;

    @Builder
    public EditMyPersonalDto(String username, String nickname, String emailId, String emailDomain,
                             String startPhoneNumber, String middlePhoneNumber, String endPhoneNumber) {
        this.username = username;
        this.nickname = nickname;
        this.emailDomain = emailDomain;
        this.emailId = emailId;
        this.startPhoneNumber = startPhoneNumber;
        this.middlePhoneNumber = middlePhoneNumber;
        this.endPhoneNumber = endPhoneNumber;
    }
}
