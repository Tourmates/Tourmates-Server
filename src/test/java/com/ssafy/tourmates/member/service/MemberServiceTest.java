package com.ssafy.tourmates.member.service;

import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import com.ssafy.tourmates.member.service.dto.JoinMemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.ssafy.tourmates.member.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .id(1L)
                .loginId("ssafy1234")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .email("ssafy@ssafy.com")
                .tel("010-1234-1234")
                .birth("2000.01.01")
                .gender(MALE)
                .nickname("ssafy")
                .build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("회원가입")
    void joinMember() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
                .loginId("tourmates1234")
                .loginPw("tourmates1234!")
                .name("김투어")
                .email("tourmates@tourmates.com")
                .tel("010-1234-5678")
                .birth("2000.01.01")
                .gender(MALE)
                .nickname("tourmates")
                .build();

        //when
        Long memberId = memberService.joinMember(dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("회원가입#아이디중복")
    void joinMemberDuplicateLoginId() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
                .loginId("ssafy1234")
                .build();

        //when
        //then
        assertThatThrownBy(() -> memberService.joinMember(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입#이메일중복")
    void joinMemberDuplicateEmail() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
                .email("ssafy@ssafy.com")
                .build();

        //when
        //then
        assertThatThrownBy(() -> memberService.joinMember(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입#연락처중복")
    void joinMemberDuplicateTel() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
                .tel("010-1234-1234")
                .build();

        //when
        //then
        assertThatThrownBy(() -> memberService.joinMember(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입#닉네임중복")
    void joinMemberDuplicateNickname() {
        //given
        JoinMemberDto dto = JoinMemberDto.builder()
                .nickname("ssafy")
                .build();

        //when
        //then
        assertThatThrownBy(() -> memberService.joinMember(dto))
                .isInstanceOf(DuplicateException.class);
    }
}