package com.ssafy.tourmates.client.api.dto.member.response;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberDetailResponse {

    private String username;
    private String nickname;
    private String birth;
    private String emailId;
    private String emailDomain;
    private String startPhoneNumber;
    private String middlePhoneNumber;
    private String endPhoneNumber;

    @Builder
    public MemberDetailResponse(String username, String nickname, String birth, String emailId, String emailDomain, String startPhoneNumber, String middlePhoneNumber, String endPhoneNumber) {
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.emailId = emailId;
        this.emailDomain = emailDomain;
        this.startPhoneNumber = startPhoneNumber;
        this.middlePhoneNumber = middlePhoneNumber;
        this.endPhoneNumber = endPhoneNumber;
    }
}
