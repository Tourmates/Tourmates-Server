package com.ssafy.tourmates.client.tripPlan;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;
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

    @OneToMany(mappedBy = "tripPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailTripPlan> detailTripPlans;

    @Builder
    public TripPlan(Long id, String title, int hit, Active active, Member member, List<DetailTripPlan> detailTripPlans) {
        this.id = id;
        this.title = title;
        this.hit = hit;
        this.active = active;
        this.member = member;
        this.detailTripPlans = detailTripPlans;
    }

    //== 연관관계 메서드 ==//
    public static TripPlan createTripPlan(String title, Long memberId, List<Integer> contentIds) {
        TripPlan tripPlan = TripPlan.builder()
                .title(title)
                .hit(0)
                .active(ACTIVE)
                .member(Member.builder().id(memberId).build())
                .build();

        List<DetailTripPlan> detailTripPlans = new ArrayList<>();
        for (Integer contentId : contentIds) {
            detailTripPlans.add(DetailTripPlan.builder()
                    .tripPlan(tripPlan)
                    .attractionInfo(AttractionInfo.builder().id(contentId).build())
                    .build());
        }

        tripPlan.detailTripPlans = detailTripPlans;
        return tripPlan;
    }

    //==비즈니스 로직==//
    public void changeTripPlan(String title, List<DetailTripPlan> detailTripPlanList) {
        this.title = title;
        this.detailTripPlans = detailTripPlanList;
    }

    public void deActive() {
        this.active = DEACTIVE;
    }
}
