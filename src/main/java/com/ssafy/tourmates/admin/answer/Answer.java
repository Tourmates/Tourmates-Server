package com.ssafy.tourmates.admin.answer;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.client.question.Question;
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
public class Answer extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;
    @Lob
    @Column(nullable = false, updatable = false)
    private String content;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Builder
    public Answer(Long id, String content, Question question, Admin admin) {
        this.id = id;
        this.content = content;
        this.question = question;
        this.admin = admin;
    }
}
