package com.ssafy.tourmates.client.friend;

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
public class Friend extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "member_id", name = "member_id")
    private Member loginMember;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "member_id", name = "target_id")
    private Member targetMember;

    @Builder
    public Friend(Long id, Member loginMember, Member targetMember){
        this.id = id;
        this.loginMember = loginMember;
        this.targetMember = targetMember;
    }
}
