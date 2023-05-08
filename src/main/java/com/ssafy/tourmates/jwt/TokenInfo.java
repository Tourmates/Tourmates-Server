package com.ssafy.tourmates.jwt;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenInfo {

    private String grantType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public TokenInfo(String grantType, String accessToken, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
