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
public class HotPlaceComment extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_comment_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public HotPlaceComment(Long id, String content, Member member, HotPlace hotPlace) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.hotPlace = hotPlace;
    }

    //== 비즈니스 로직 ==//
    public void changeComment(String content){
        this.content = content;
    }
}
