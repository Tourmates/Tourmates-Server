package com.ssafy.tourmates.client.api.dto.hotplace.response;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;

@Data
public class EditHotPlaceResponse {

    private Long hotPlaceId;
    private ContentType tag;
    private String title;
    private String visitedDate;
    private String content;

    private String attractionTitle;
    private Double latitude;
    private Double longitude;

    @Builder
    public EditHotPlaceResponse(Long hotPlaceId, ContentType tag, String title, String visitedDate, String content, String attractionTitle, Double latitude, Double longitude) {
        this.hotPlaceId = hotPlaceId;
        this.tag = tag;
        this.title = title;
        this.visitedDate = visitedDate;
        this.content = content;
        this.attractionTitle = attractionTitle;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
