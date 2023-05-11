package com.ssafy.tourmates.notice.service.impl;

import com.ssafy.tourmates.controller.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.controller.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.notice.repository.NoticeQueryRepository;
import com.ssafy.tourmates.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.notice.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeQueryServiceImpl implements NoticeQueryService {

    private final NoticeQueryRepository noticeQueryRepository;

    @Override
    public List<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable) {
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
