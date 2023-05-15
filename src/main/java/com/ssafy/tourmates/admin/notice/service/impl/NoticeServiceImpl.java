package com.ssafy.tourmates.admin.notice.service.impl;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.validator.AdminValidator;
import com.ssafy.tourmates.admin.notice.Notice;
import com.ssafy.tourmates.admin.notice.repository.NoticeRepository;
import com.ssafy.tourmates.admin.notice.service.NoticeService;
import com.ssafy.tourmates.admin.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.admin.notice.validator.NoticeValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.admin.notice.service.dto.EditNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.tourmates.client.member.Active.*;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeValidator noticeValidator;
    private final AdminValidator adminValidator;

    @Override
    public Long registerNotice(String loginId, AddNoticeDto dto) {
        Admin findMember = adminValidator.findByLoginId(loginId);
        Notice notice = Notice.builder()
                .pin(dto.getPin())
                .title(dto.getTitle())
                .content(dto.getContent())
                .active(ACTIVE)
                .admin(findMember)
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

    @Override
    public Long removeNotice(Long noticeId) {
        Notice findNotice = noticeValidator.findById(noticeId);
        findNotice.deActive();
        return findNotice.getId();
    }

}
