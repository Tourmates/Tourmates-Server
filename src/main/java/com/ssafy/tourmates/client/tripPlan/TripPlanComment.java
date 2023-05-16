package com.ssafy.tourmates.client.tripPlan;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TripPlanComment extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "trip_plan_comment")
    private Long id;
    @Column(nullable = false, length = 50)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_plan_id")
    private TripPlan tripPlan;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public TripPlanComment(Long id, String content, TripPlan tripPlan, Member member) {
        this.id = id;
        this.content = content;
        this.tripPlan = tripPlan;
        this.member = member;
    }

    public void changeTripPlanComment(String content) {
        this.content = content;
    }

}
