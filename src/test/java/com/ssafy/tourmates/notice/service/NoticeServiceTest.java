package com.ssafy.tourmates.notice.service;

import com.ssafy.tourmates.member.Active;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import com.ssafy.tourmates.notice.Notice;
import com.ssafy.tourmates.notice.repository.NoticeRepository;
import com.ssafy.tourmates.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.notice.service.dto.EditNoticeDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.tourmates.member.Active.*;
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
    private Notice savedNotice;

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

    @Test
    @DisplayName("공지사항 수정")
    void editNotice() {
        //given
        createNotice();
        EditNoticeDto dto = EditNoticeDto.builder()
                .pin("1")
                .title("수정된 공지사항 제목")
                .content("수정된 공지사항 내용입니다.")
                .build();

        //when
        Long noticeId = noticeService.editNotice(savedNotice.getId(), dto);

        //then
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        assertThat(findNotice).isPresent();
        assertThat(findNotice.get().getTitle()).isEqualTo(dto.getTitle());
    }

    private void createNotice() {
        Notice notice = Notice.builder()
                .pin("0")
                .title("공지사항 제목")
                .content("공지사항 내용입니다.")
                .active(ACTIVE)
                .member(savedMember)
                .build();
        savedNotice = noticeRepository.save(notice);
    }
}