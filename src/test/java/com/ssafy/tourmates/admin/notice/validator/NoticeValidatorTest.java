package com.ssafy.tourmates.admin.notice.validator;

import com.ssafy.tourmates.admin.notice.validator.NoticeValidator;
import com.ssafy.tourmates.admin.notice.Notice;
import com.ssafy.tourmates.admin.notice.repository.NoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.tourmates.client.member.Active.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class NoticeValidatorTest {

    @Autowired
    private NoticeValidator noticeValidator;
    @Autowired
    private NoticeRepository noticeRepository;

    private Notice savedNotice;

    @BeforeEach
    void beforeEach() {
        Notice notice = Notice.builder()
                .pin("0")
                .title("공지사항 제목")
                .content("공지사항 내용입니다.")
                .active(ACTIVE)
                .build();
        savedNotice = noticeRepository.save(notice);
    }

    @Test
    @DisplayName("공지사항 PK로 조회")
    void findById() {
        //given

        //when
        Notice findNotice = noticeValidator.findById(savedNotice.getId());

        //then
        assertThat(findNotice.getId()).isEqualTo(savedNotice.getId());
    }
}