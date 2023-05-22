package com.ssafy.tourmates.admin.api.dto.attraction.response;

import lombok.Data;

@Data
public class GugunResponse {

    private Integer gugunCode;
    private String name;

    public GugunResponse(Integer gugunCode, String name) {
        this.gugunCode = gugunCode;
        this.name = name;
    }
}
