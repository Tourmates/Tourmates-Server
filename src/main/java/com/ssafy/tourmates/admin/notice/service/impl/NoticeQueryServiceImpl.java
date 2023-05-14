package com.ssafy.tourmates.admin.notice.service.impl;

import com.ssafy.tourmates.admin.notice.repository.NoticeQueryRepository;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
import com.ssafy.tourmates.admin.controller.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeQueryServiceImpl implements NoticeQueryService {

    private final NoticeQueryRepository noticeQueryRepository;

    @Override
    public Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable) {
        return noticeQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public List<NoticeResponse> searchPinNotices() {
        return noticeQueryRepository.searchPinNotices();
    }

    @Override
    public DetailNoticeResponse searchNotice(Long noticeId) {
        return noticeQueryRepository.searchNotice(noticeId);
    }
}
