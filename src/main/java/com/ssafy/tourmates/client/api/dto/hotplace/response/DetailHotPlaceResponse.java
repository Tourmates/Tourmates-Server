package com.ssafy.tourmates.client.api.dto.hotplace.response;

import com.ssafy.tourmates.common.domain.ContentType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DetailHotPlaceResponse {

    private Long hotPlaceId;
    private ContentType tag;
    private String title;
    private String content;
    private int hit;
    private String visitedDate;

    private Boolean isMine;
    private String nickname;
    private List<String> images;
    private List<String> comments;

    private String attractionTitle;
    private Double latitude;
    private Double longitude;

    @Builder
    public DetailHotPlaceResponse(Long hotPlaceId, ContentType tag, String title, String content, int hit, String visitedDate, Boolean isMine, String nickname, List<String> images, List<String> comments, String attractionTitle, Double latitude, Double longitude) {
        this.hotPlaceId = hotPlaceId;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.isMine = isMine;
        this.nickname = nickname;
        this.images = images;
        this.comments = comments;
        this.attractionTitle = attractionTitle;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
