package com.ssafy.tourmates.admin.trend;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Trend extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "trend_id")
    private Long id;
    private int teenage;
    private int twenty;
    private int thirty;
    private int male;
    private int female;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @Builder
    public Trend(Long id, int teenage, int twenty, int thirty, int male, int female, AttractionInfo attractionInfo) {
        this.id = id;
        this.teenage = teenage;
        this.twenty = twenty;
        this.thirty = thirty;
        this.male = male;
        this.female = female;
        this.attractionInfo = attractionInfo;
    }

    //== 비즈니스 로직 ==//
    public void increaseTeenage() {
        this.teenage += 1;
    }

    public void increaseTwenty() {
        this.twenty += 1;
    }

    public void increaseThirty() {
        this.thirty += 1;
    }

    public void increaseMale() {
        this.male += 1;
    }

    public void increaseFemale() {
        this.female += 1;
    }
}
