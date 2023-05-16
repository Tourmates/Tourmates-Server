package com.ssafy.tourmates.admin.controller.dto.member.response;

import com.ssafy.tourmates.client.member.Active;
import lombok.Data;

import static com.ssafy.tourmates.client.member.Active.*;

@Data
public class MemberResponse {

    private Long memberId;
    private String name;
    private String email;
    private String birth;
    private String nickname;
    private Boolean active;

    public MemberResponse(Long memberId, String name, String email, String birth, String nickname, Active active) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.nickname = nickname;
        this.active = (active == ACTIVE);
    }
}
