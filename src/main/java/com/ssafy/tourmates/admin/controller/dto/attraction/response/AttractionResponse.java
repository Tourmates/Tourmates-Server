package com.ssafy.tourmates.admin.controller.dto.attraction.response;

import lombok.Data;

@Data
public class AttractionResponse {

    private Integer contentId;
    private String title;
    private String addr1;
    private String zipcode;
    private String tel;
    private String firstImage;
    private Double latitude;
    private Double longitude;

    public AttractionResponse(Integer contentId, String title, String addr1, String zipcode, String tel, String firstImage, Double latitude, Double longitude) {
        this.contentId = contentId;
        this.title = title;
        this.addr1 = addr1;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
//불러오기
//등록하기
//보여주기
