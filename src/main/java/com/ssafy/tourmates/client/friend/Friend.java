package com.ssafy.tourmates.client.friend;

import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Friend extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private Long id;
    @Column(nullable = false, name = "member_id")
    private Long memberId;
    @Column(nullable = false, name = "target_id")
    private Long targetId;

    @Builder
    public Friend(Long id, Long memberId, Long targetId){
        this.id = id;
        this.memberId = memberId;
        this.targetId = targetId;
    }
}
