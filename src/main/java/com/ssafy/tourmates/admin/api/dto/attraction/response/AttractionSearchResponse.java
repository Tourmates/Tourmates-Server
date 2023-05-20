package com.ssafy.tourmates.admin.api.dto.attraction.response;

import lombok.Data;

@Data
public class AttractionSearchResponse {

    private Integer contentId;
    private String title;

    public AttractionSearchResponse(Integer contentId, String title) {
        this.contentId = contentId;
        this.title = title;
    }
}
