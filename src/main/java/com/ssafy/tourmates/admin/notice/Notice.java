package com.ssafy.tourmates.admin.notice;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.ssafy.tourmates.client.member.Active.DEACTIVE;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Notice extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;
    @Column(nullable = false, length = 1)
    private String pin;
    @Column(nullable = false, length = 50)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Builder
    public Notice(Long id, String pin, String title, String content, Active active, Admin admin) {
        this.id = id;
        this.pin = pin;
        this.title = title;
        this.content = content;
        this.active = active;
        this.admin = admin;
    }

    //== 비즈니스 로직 ==//
    public void changeNotice(String pin, String title, String content) {
        this.pin = pin;
        this.title = title;
        this.content = content;
    }

    public void deActive() {
        this.active = DEACTIVE;
    }
}
