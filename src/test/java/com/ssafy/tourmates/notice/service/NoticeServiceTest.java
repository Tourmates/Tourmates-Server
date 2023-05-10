package com.ssafy.tourmates.notice.service;

import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import com.ssafy.tourmates.notice.Notice;
import com.ssafy.tourmates.notice.repository.NoticeRepository;
import com.ssafy.tourmates.notice.service.dto.AddNoticeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.tourmates.member.Gender.MALE;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRepository noticeRepository;
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
    @DisplayName("공지사항 등록")
    void registerNotice() {
        //given
        AddNoticeDto dto = AddNoticeDto.builder()
                .pin("0")
                .title("공지사항 제목")
                .content("공지사항 내용입니다.")
                .build();

        //when
        Long noticeId = noticeService.registerNotice(savedMember.getLoginId(), dto);

        //then
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        assertThat(findNotice).isPresent();
    }
}