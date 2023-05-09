package com.ssafy.tourmates.member;

import com.ssafy.tourmates.common.exception.EditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.tourmates.member.Active.*;
import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    @DisplayName("비밀번호 변경")
    void changeLoginPw() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();

        //when
        member.changeLoginPw("ssafy1234!", "ssafy5678@");

        //then
        assertThat(member.getLoginPw()).isEqualTo("ssafy5678@");
    }

    @Test
    @DisplayName("비밀번호 변경 예외")
    void changeLoginPwException() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();

        //when
        //then
        assertThatThrownBy(() -> member.changeLoginPw("ssafy1134!", "ssafy5678@"))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("이메일 변경")
    void changeEmail() {
        //given
        Member member = Member.builder()
                .email("ssafy@ssfay.com")
                .build();

        //when
        member.changeEmail("newSsafy@ssafy.com");

        //then
        assertThat(member.getEmail()).isEqualTo("newSsafy@ssafy.com");
    }

    @Test
    @DisplayName("연락처 변경")
    void changeTel() {
        //given
        String newTel = "010-5678-5678";
        Member member = Member.builder()
                .tel("010-1234-1234")
                .build();

        //when
        member.changeTel(newTel);

        //then
        assertThat(member.getTel()).isEqualTo(newTel);
    }

    @Test
    @DisplayName("닉네임 변경 예외")
    void changeNickname() {
        //given
        String newNickname = "tourmates";
        Member member = Member.builder()
                .nickname("ssafy")
                .build();
        //when
        //then
        assertThatThrownBy(() -> member.changeNickname(newNickname))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("회원탈퇴")
    void deActive() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();

        //when
        member.deActive("ssafy1234!");

        //then
        assertThat(member.getActive()).isEqualTo(DEACTIVE);
    }
    
    @Test
    @DisplayName("회원탈퇴 예외")
    void deActiveException() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();
        
        //when
        //then
        assertThatThrownBy(() -> member.deActive("ssafy5678@"))
                .isInstanceOf(EditException.class);
    }
}