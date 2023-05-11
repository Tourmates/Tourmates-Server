package com.ssafy.tourmates.admin.attraction.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionSearchCondition {

    private Integer sidoCode;
    private Integer gugunCode;

    @Builder
    public AttractionSearchCondition(Integer sidoCode, Integer gugunCode) {
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
    }
}
