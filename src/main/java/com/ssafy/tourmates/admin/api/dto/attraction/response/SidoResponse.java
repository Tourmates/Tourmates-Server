package com.ssafy.tourmates.admin.api.dto.attraction.response;

import lombok.Data;

@Data
public class SidoResponse {

    private Integer sidoCode;
    private String name;

    public SidoResponse(Integer sidoCode, String name) {
        this.sidoCode = sidoCode;
        this.name = name;
    }
}
