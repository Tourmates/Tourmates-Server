package com.ssafy.tourmates.client.api.dto.hotplace.response;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EditHotPlaceResponse {

    private Long hotPlaceId;
    private String title;
    private String visitedDate;
    private String content;
    private String attractionTitle;
    private Double latitude;
    private Double longitude;
    private List<String> hashtags;

    @Builder
    public EditHotPlaceResponse(Long hotPlaceId, String title, String visitedDate, String content, String attractionTitle, Double latitude, Double longitude, List<String> hashtags) {
        this.hotPlaceId = hotPlaceId;
        this.title = title;
        this.visitedDate = visitedDate;
        this.content = content;
        this.attractionTitle = attractionTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hashtags = hashtags;
    }
}
