package com.ssafy.tourmates.member.service;

import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import com.ssafy.tourmates.member.service.dto.EditLoginPwDto;
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

    private Member savedMember;

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
        savedMember = memberRepository.save(member);
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

    @Test
    @DisplayName("비밀번호변경")
    void editLoginPw() {
        //given
        EditLoginPwDto dto = EditLoginPwDto.builder()
                .currentLoginPw("ssafy1234!")
                .newLoginPw("ssafy5678@")
                .build();

        //when
        Long memberId = memberService.editLoginPw(savedMember.getLoginId(), dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getLoginPw()).isEqualTo(dto.getNewLoginPw());
    }

    @Test
    @DisplayName("이메일변경")
    void editEmail() {
        //given
        String newEmail = "newSsafy@ssafy.com";

        //when
        Long memberId = memberService.editEmail(savedMember.getLoginId(), newEmail);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getEmail()).isEqualTo(newEmail);
    }

    @Test
    @DisplayName("이메일변경#이메일중복")
    void editEmailDuplicate() {
        //given
        createMember();

        //when
        //then
        assertThatThrownBy(() -> memberService.editEmail(savedMember.getLoginId(), "ssafy1@ssafy.com"))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("연락처변경")
    void editTel() {
        //given
        String newTel = "010-5678-5678";
        
        //when
        Long memberId = memberService.editTel(savedMember.getLoginId(), newTel);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getTel()).isEqualTo(newTel);
    }
    
    @Test
    @DisplayName("연락처변경#연락처중복")
    void editTelDuplicate() {
        //given
        createMember();
        
        //when
        //then
        assertThatThrownBy(() -> memberService.editTel(savedMember.getLoginId(), "010-1111-2222"))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("닉네임변경")
    void editNickname() {
        //given
        String newNickname = "tourmates";

        //when
        Long memberId = memberService.editNickname(savedMember.getLoginId(), newNickname);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getNickname()).isEqualTo(newNickname);
    }
    
    @Test
    @DisplayName("닉네임변경#닉네임중복")
    void editNicknameDuplicate() {
        //given
        createMember();
        
        //when
        //then
        assertThatThrownBy(() -> memberService.editNickname(savedMember.getLoginId(), "ssafy1"))
                .isInstanceOf(DuplicateException.class);
    }

    private void createMember() {
        Member member = Member.builder()
                .id(2L)
                .loginId("ssafy5678")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .email("ssafy1@ssafy.com")
                .tel("010-1111-2222")
                .birth("2000.01.01")
                .gender(MALE)
                .nickname("ssafy1")
                .build();
        memberRepository.save(member);
    }
}