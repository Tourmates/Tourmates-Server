package com.ssafy.tourmates.client.tripPlan;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.*;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class DetailPlan extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "detail_plan_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_plan_id")
    private TripPlan tripPlan;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @Builder
    public DetailPlan(Long id, TripPlan tripPlan, AttractionInfo attractionInfo){
        this.id = id;
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
    }

    //==비즈니스 로직==/
    public void changeDetailPlan(AttractionInfo attractionInfo){
        this.attractionInfo = attractionInfo;
    }


}
