package com.ssafy.tourmates.client.api.dto.tripplan.response;

import lombok.Data;

@Data
public class DetailPlanDataResponse {
    private String title;
    private String addr1;
    private String zipcode;
    private String image;
    private Double latitude;
    private Double longitude;

    public DetailPlanDataResponse(String title, String addr1, String zipcode, String image, Double latitude, Double longitude) {
        this.title = title;
        this.addr1 = addr1;
        this.zipcode = zipcode;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
