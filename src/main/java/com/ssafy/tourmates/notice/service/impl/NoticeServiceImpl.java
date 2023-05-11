package com.ssafy.tourmates.notice.service.impl;

import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.validator.MemberValidator;
import com.ssafy.tourmates.notice.Notice;
import com.ssafy.tourmates.notice.repository.NoticeRepository;
import com.ssafy.tourmates.notice.service.NoticeService;
import com.ssafy.tourmates.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.notice.service.dto.EditNoticeDto;
import com.ssafy.tourmates.notice.validator.NoticeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.tourmates.member.Active.*;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeValidator noticeValidator;
    private final MemberValidator memberValidator;

    @Override
    public Long registerNotice(String loginId, AddNoticeDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);
        Notice notice = Notice.builder()
                .pin(dto.getPin())
                .title(dto.getTitle())
                .content(dto.getContent())
                .active(ACTIVE)
                .member(findMember)
                .build();
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getId();
    }

    @Override
    public Long editNotice(Long noticeId, EditNoticeDto dto) {
        Notice findNotice = noticeValidator.findById(noticeId);
        findNotice.changeNotice(dto.getPin(), dto.getTitle(), dto.getContent());
        return findNotice.getId();
    }

}
