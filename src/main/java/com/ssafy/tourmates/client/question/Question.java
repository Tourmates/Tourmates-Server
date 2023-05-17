package com.ssafy.tourmates.client.question;

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
public class Question extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuestionType type;
    @Column(nullable = false, updatable = false, length = 50)
    private String title;
    @Lob
    @Column(nullable = false, updatable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Question(Long id, QuestionType type, String title, String content, Member member) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
