package com.ssafy.tourmates.admin.admin;

import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Admin extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @Builder
    public Admin(Long id, String loginId, String loginPw, String name, String email, String tel, Active active) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.active = active;
    }
}
