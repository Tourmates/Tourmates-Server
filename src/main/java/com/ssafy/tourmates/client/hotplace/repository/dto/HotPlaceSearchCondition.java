package com.ssafy.tourmates.client.hotplace.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceSearchCondition {

    private Integer type;
    private String keyword;

    @Builder
    public HotPlaceSearchCondition(Integer type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }
}
