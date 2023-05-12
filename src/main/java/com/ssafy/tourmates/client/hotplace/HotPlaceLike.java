package com.ssafy.tourmates.client.hotplace;

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
public class HotPlaceLike extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_palce_favorite_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public HotPlaceLike(Long id, HotPlace hotPlace, Member member) {
        this.id = id;
        this.hotPlace = hotPlace;
        this.member = member;
    }

}
