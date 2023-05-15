package com.ssafy.tourmates.client.tripPlan;

import static com.ssafy.tourmates.client.member.Active.DEACTIVE;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TripPlan extends TimeBaseEntity {

    @Id @GeneratedValue
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

    @Builder
    public TripPlan(Long id, String title, int hit, Active active, Member member){
        this.id = id;
        this.title = title;
        this.hit = hit;
        this.active = active;
        this.member = member;
    }

    //==비즈니스 로직==//
    public void changeTripPlan(String title){
    this.title = title;
  }

    public void deActive() {
        this.active = DEACTIVE;
    }
}
