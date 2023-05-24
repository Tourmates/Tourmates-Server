package com.ssafy.tourmates.client.api.dto.tripplan.request;

import lombok.Builder;
import lombok.Data;

@Data
public class TripPlanRequest{

    private String addr1;
    private Long contentTypeId;
    private Double latitude;
    private Double longitude;
    private String title;

    @Builder
    public TripPlanRequest(String addr1, Long contentTypeId, Double latitude, Double longitude, String title){
        this.addr1 = addr1;
        this.contentTypeId = contentTypeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }
}