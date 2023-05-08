package com.ssafy.tourmates.member;

import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static com.ssafy.tourmates.member.Active.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;
    @Column(nullable = false, length = 20)
    private String loginPw;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(unique = true, nullable = false, length = 13)
    private String tel;
    @Column(nullable = false, updatable = false, length = 10)
    private String birth;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private Gender gender;
    @Column(unique = true, nullable = false, length = 10)
    private String nickname;
    @Column(nullable = false)
    private LocalDateTime nicknameLastModifiedDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String email, String tel, String birth, Gender gender, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.nicknameLastModifiedDate = LocalDateTime.now();
        this.active = ACTIVE;
    }
}
