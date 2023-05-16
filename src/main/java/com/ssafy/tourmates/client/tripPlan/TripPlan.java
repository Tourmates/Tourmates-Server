package com.ssafy.tourmates.client.tripPlan;

import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.ssafy.tourmates.client.member.Active.DEACTIVE;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TripPlan extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "trip_plan_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    private int hit;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "detail_trip_plan_id")
    private List<DetailTripPlan> detailTripPlanList;

    @Builder
    public TripPlan(Long id, String title, int hit, Active active, Member member, List<DetailTripPlan> detailTripPlanList) {
        this.id = id;
        this.title = title;
        this.hit = hit;
        this.active = active;
        this.member = member;
        this.detailTripPlanList = detailTripPlanList;
    }

    //==비즈니스 로직==//
    public void changeTripPlan(String title, List<DetailTripPlan> detailTripPlanList) {
        this.title = title;
        this.detailTripPlanList = detailTripPlanList;
    }

    public void deActive() {
        this.active = DEACTIVE;
    }
}
