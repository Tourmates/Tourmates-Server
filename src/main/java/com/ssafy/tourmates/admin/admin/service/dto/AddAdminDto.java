package com.ssafy.tourmates.admin.admin.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddAdminDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String email;
    private String tel;

    @Builder
    public AddAdminDto(String loginId, String loginPw, String name, String email, String tel) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.email = email;
        this.tel = tel;
    }
}
