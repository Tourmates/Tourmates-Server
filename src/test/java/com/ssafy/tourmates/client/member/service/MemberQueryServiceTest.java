package com.ssafy.tourmates.client.member.service;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import com.ssafy.tourmates.client.member.service.MemberQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.tourmates.client.member.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberQueryServiceTest {

    @Autowired
    private MemberQueryService memberQueryService;
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
    @DisplayName("이메일로 아이디 찾기")
    void forgotLoginIdByEmail() {
        //given

        //when
        String findLoginId = memberQueryService.forgotLoginIdByEmail("ssafy@ssafy.com");

        //then
        assertThat(findLoginId).isEqualTo("ssafy1234");
    }

    @Test
    @DisplayName("연락처로 아이디 찾기")
    void forgotLoginIdByTel() {
        //given
            
        //when
        String findLoginId = memberQueryService.forgotLoginIdByTel("010-1234-1234");

        //then
        assertThat(findLoginId).isEqualTo("ssafy1234");
    }

    @Test
    @DisplayName("이메일로 비밀번호 찾기")
    void forgotLoginPwByEmail() {
        //given

        //when
        String findLoginPw = memberQueryService.forgotLoginPwByEmail("ssafy1234", "ssafy@ssafy.com");

        //then
        assertThat(findLoginPw).isEqualTo("ssafy1234!");
    }

    @Test
    @DisplayName("연락처로 비밀번호 찾기")
    void forgotLoginPwByTel() {
        //given

        //when
        String findLoginPw = memberQueryService.forgotLoginPwByTel("ssafy1234", "010-1234-1234");

        //then
        assertThat(findLoginPw).isEqualTo("ssafy1234!");
    }
}