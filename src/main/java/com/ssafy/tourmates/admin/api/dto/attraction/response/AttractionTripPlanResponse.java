package com.ssafy.tourmates.admin.api.dto.attraction.response;

import lombok.Data;

@Data
public class AttractionTripPlanResponse {

    private Integer contentId;
    private String title;
    private String addr1;
    private Double latitude;
    private Double longitude;

    public AttractionTripPlanResponse(Integer contentId, String title, String addr1, Double latitude, Double longitude) {
        this.contentId = contentId;
        this.title = title;
        this.addr1 = addr1;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
