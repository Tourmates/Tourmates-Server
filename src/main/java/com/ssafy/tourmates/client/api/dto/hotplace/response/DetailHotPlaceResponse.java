package com.ssafy.tourmates.client.api.dto.hotplace.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DetailHotPlaceResponse {

    private Long hotPlaceId;
    private String title;
    private String content;
    private int hit;
    private String visitedDate;

    private Boolean isMine;
    private String nickname;
    private List<String> images;

    private String attractionTitle;
    private Double latitude;
    private Double longitude;

    private List<String> tags;

    @Builder
    public DetailHotPlaceResponse(Long hotPlaceId, String title, String content, int hit, String visitedDate, Boolean isMine, String nickname, List<String> images, String attractionTitle, Double latitude, Double longitude, List<String> tags) {
        this.hotPlaceId = hotPlaceId;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.isMine = isMine;
        this.nickname = nickname;
        this.images = images;
        this.attractionTitle = attractionTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
    }
}
