package com.ssafy.tourmates.admin.api.dto.attraction.response;

import lombok.Data;

@Data
public class AttractionSearchResponse {

    private Integer contentId;
    private String title;
    private double latitude;
    private double longitude;

    public AttractionSearchResponse(Integer contentId, String title, double latitude, double longitude) {
        this.contentId = contentId;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
