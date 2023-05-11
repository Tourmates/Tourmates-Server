package com.ssafy.tourmates.client.controller.dto.hotplace.response;

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

    private String nickname;
    private List<String> images;

    @Builder
    public DetailHotPlaceResponse(Long hotPlaceId, ContentType tag, String title, String content, int hit, String visitedDate, String nickname, List<String> images) {
        this.hotPlaceId = hotPlaceId;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.visitedDate = visitedDate;
        this.nickname = nickname;
        this.images = images;
    }
}
