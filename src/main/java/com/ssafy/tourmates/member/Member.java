package com.ssafy.tourmates.member;

import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import com.ssafy.tourmates.common.exception.EditException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.tourmates.member.Active.ACTIVE;
import static javax.persistence.FetchType.EAGER;

@Entity
@Getter
public class Member extends TimeBaseEntity implements UserDetails {

    @Id
    @GeneratedValue
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
    @ElementCollection(fetch = EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Member() {
    }

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String email, String tel, String birth, Gender gender, String nickname, List<String> roles) {
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
        this.roles = roles;
    }

    //== 비즈니스 로직 ==//
    public void changeLoginPw(String currentLoginPw, String newLoginPw) {
        if (!this.loginPw.equals(currentLoginPw)) {
            throw new EditException();
        }
        this.loginPw = newLoginPw;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeTel(String tel) {
        this.tel = tel;
    }

    public void changeNickname(String nickname) {
        LocalDateTime date = LocalDateTime.now().minusDays(30);
        if (date.isAfter(this.nicknameLastModifiedDate)) {
            throw new EditException();
        }
        this.nickname = nickname;
        this.nicknameLastModifiedDate = LocalDateTime.now();
    }

    //== 스프링 시큐리티 ==//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
