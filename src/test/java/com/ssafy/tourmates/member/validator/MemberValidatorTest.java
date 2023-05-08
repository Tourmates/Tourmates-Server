package com.ssafy.tourmates.member.validator;

import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.ssafy.tourmates.member.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberValidatorTest {

    @Autowired
    private MemberValidator memberValidator;
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
    @DisplayName("회원조회#아이디")
    void findByLoginId() {
        //given

        //when
        Member findMember = memberValidator.findByLoginId("ssafy1234");

        //then
        assertThat(findMember.getEmail()).isEqualTo("ssafy@ssafy.com");
    }

    @Test
    @DisplayName("회원조회#아이디예외")
    void findByLoginIdException() {
        //given

        //when
        //then
        assertThatThrownBy(() -> memberValidator.findByLoginId("tourmates1234"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("회원조회#이메일")
    void findByEmail() {
        //given

        //when
        Member findMember = memberValidator.findByEmail("ssafy@ssafy.com");

        //then
        assertThat(findMember.getLoginId()).isEqualTo("ssafy1234");
    }

    @Test
    @DisplayName("회원조회#이메일예외")
    void findByEmailException() {
        //given

        //when
        //then
        assertThatThrownBy(() -> memberValidator.findByEmail("tourmates@tourmates.com"))
                .isInstanceOf(NoSuchElementException.class);
    }
    
    @Test
    @DisplayName("회원조회#연락처")
    void findByTel() {
        //given
            
        //when
        Member findMember = memberValidator.findByTel("010-1234-1234");

        //then
        assertThat(findMember.getLoginId()).isEqualTo("ssafy1234");
    }

    @Test
    @DisplayName("회원조회#연락처예외")
    void findByTelException() {
        //given

        //when
        //then
        assertThatThrownBy(() -> memberValidator.findByTel("010-1234-5678"))
                .isInstanceOf(NoSuchElementException.class);
    }
}