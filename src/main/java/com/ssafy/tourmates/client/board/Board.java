package com.ssafy.tourmates.client.board;

import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false)
    private String content;
    private int hit;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Board(Long id, String title, String content, int hit, Active active, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.active = active;
        this.member = member;
    }
}
