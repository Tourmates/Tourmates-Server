package com.ssafy.tourmates.admin.controller.dto.admin;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginAdmin {

    private String loginId;
    private String name;

    @Builder
    public LoginAdmin(String loginId, String name) {
        this.loginId = loginId;
        this.name = name;
    }
}
