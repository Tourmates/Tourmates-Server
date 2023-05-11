package com.ssafy.tourmates.client.member.service.dto;

import com.ssafy.tourmates.client.member.Gender;
import lombok.Builder;
import lombok.Data;

@Data
public class JoinMemberDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String email;
    private String tel;
    private String birth;
    private Gender gender;
    private String nickname;

    @Builder
    public JoinMemberDto(String loginId, String loginPw, String name, String email, String tel, String birth, Gender gender, String nickname) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }
}
