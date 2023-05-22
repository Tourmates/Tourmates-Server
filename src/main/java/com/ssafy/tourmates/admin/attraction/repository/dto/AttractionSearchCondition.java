package com.ssafy.tourmates.admin.attraction.repository.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AttractionSearchCondition {

    private String keyword;
    private Integer sidoCode;
    private Integer gugunCode;
    private List<Integer> contentTypeIds;

    @Builder
    public AttractionSearchCondition(String keyword, Integer sidoCode, Integer gugunCode, List<Integer> contentTypeIds) {
        this.keyword = keyword;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.contentTypeIds = contentTypeIds;
    }
}
