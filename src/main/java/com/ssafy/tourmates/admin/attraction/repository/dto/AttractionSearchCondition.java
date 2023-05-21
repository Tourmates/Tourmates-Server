package com.ssafy.tourmates.admin.attraction.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AttractionSearchCondition {

    private String keyword;
    private Integer sidoCode;
    private Integer gugunCode;
    private Integer contentTypeId;

    @Builder
    public AttractionSearchCondition(String keyword, Integer sidoCode, Integer gugunCode, Integer contentTypeId) {
        this.keyword = keyword;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.contentTypeId = contentTypeId;
    }
}
