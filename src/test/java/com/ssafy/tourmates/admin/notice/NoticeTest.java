package com.ssafy.tourmates.admin.notice;

import com.ssafy.tourmates.admin.notice.Notice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.tourmates.client.member.Active.*;
import static org.assertj.core.api.Assertions.*;

class NoticeTest {

    @Test
    @DisplayName("공지사항 변경")
    void changeNotice() {
        //given
        Notice notice = Notice.builder()
                .pin("0")
                .title("변경전 공지사항 제목")
                .content("변경전 공지사항 내용")
                .build();

        //when
        notice.changeNotice("1", "변경후 공지사항 제목", "변경후 공지사항 내용");

        //then
        assertThat(notice.getTitle()).isEqualTo("변경후 공지사항 제목");
    }
    
    @Test
    @DisplayName("공지사항 비활성화")
    void deActive() {
        //given
        Notice notice = Notice.builder()
                .active(ACTIVE)
                .build();
        
        //when
        notice.deActive();
        
        //then
        assertThat(notice.getActive()).isEqualTo(DEACTIVE);
    }

}