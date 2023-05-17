package com.ssafy.tourmates.client.api.dto.hotplace.response;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;

@Data
public class HotPlaceResponse {

    private Long hotPlaceId;
    private ContentType tag;
    private String title;
    private String content;
    private int hit;
    private String visitedDate;

    private String uploadFileName;

    @Builder
    public HotPlaceResponse(Long hotPlaceId, ContentType tag, String title, String content, int hit, String visitedDate, String uploadFileName) {
        this.hotPlaceId = hotPlaceId;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.uploadFileName = uploadFileName;
    }
}
