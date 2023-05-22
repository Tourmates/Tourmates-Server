package com.ssafy.tourmates.client.hotplace.repository.dto;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class HotPlaceSearchCondition {

    private List<ContentType> tags;
    private String keyword;

    @Builder
    public HotPlaceSearchCondition(List<ContentType> tags, String keyword) {
        this.tags = tags;
        this.keyword = keyword;
    }
}
