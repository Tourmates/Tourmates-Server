package com.ssafy.tourmates.admin.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AttractionInfo {

    @Id
    @Column(name = "content_id")
    private Integer id;
    private Integer contentTypeId;
    @Column(length = 100)
    private String title;
    @Column(length = 100)
    private String addr1;
    @Column(length = 50)
    private String addr2;
    @Column(length = 50)
    private String zipcode;
    @Column(length = 50)
    private String tel;
    @Column(length = 200)
    private String firstImage;
    @Column(length = 200)
    private String firstImage2;
    private int readcount;
    @Column(precision = 20, scale = 17)
    private double latitude;
    @Column(precision = 20, scale = 17)
    private double longitude;
    @Column(length = 2)
    private String mlevel;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "gugun_code")
    private Gugun gugun;

    @Builder
    public AttractionInfo(Integer id, Integer contentTypeId, String title, String addr1, String addr2, String zipcode, String tel, String firstImage, String firstImage2, int readcount, double latitude, double longitude, String mlevel, Sido sido, Gugun gugun) {
        this.id = id;
        this.contentTypeId = contentTypeId;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.readcount = readcount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mlevel = mlevel;
        this.sido = sido;
        this.gugun = gugun;
    }

    //== 비즈니스 로직 ==//
    public void increaseReadcount() {
        this.readcount += 1;
    }
}
