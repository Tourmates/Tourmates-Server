package com.ssafy.tourmates.client.hotplace.repository.dto;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceSearchCondition {

    private ContentType tag;
    private String title;
    private String content;

    @Builder
    public HotPlaceSearchCondition(ContentType tag, String title, String content) {
        this.tag = tag;
        this.title = title;
        this.content = content;
    }
}
