package com.ssafy.tourmates.client.api.dto.hotplace.response;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class HotPlaceResponse {

    private Long hotPlaceId;
    private ContentType tag;
    private String title;
    private String content;
    private Integer hit;
    private String visitedDate;

    private String storeFileName;
    private List<String> tags;

    @Builder
    public HotPlaceResponse(Long hotPlaceId, ContentType tag, String title, String content, Integer hit, String visitedDate, String storeFileName, List<String> tags) {
        this.hotPlaceId = hotPlaceId;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.storeFileName = storeFileName;
        this.tags = tags;
    }
}
