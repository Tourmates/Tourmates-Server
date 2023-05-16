package com.ssafy.tourmates.admin.notice.service.impl;

import com.ssafy.tourmates.admin.api.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.EditNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.AdminNoticeResponse;
import com.ssafy.tourmates.admin.notice.repository.NoticeQueryRepository;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
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
    public Long getTotalCount(NoticeSearchCondition condition) {
        return noticeQueryRepository.totalCount(condition);
    }

    @Override
    public DetailNoticeResponse searchNotice(Long noticeId) {
        return noticeQueryRepository.searchNotice(noticeId);
    }

    @Override
    public EditNoticeResponse searchEditNotice(Long noticeId) {
        return noticeQueryRepository.searchEditNotice(noticeId);
    }

    @Override
    public List<AdminNoticeResponse> searchAdminNotices() {
        return noticeQueryRepository.searchAdminNotices();
    }

    @Override
    public Long bulkDeActive(List<Long> noticeIds) {
        return noticeQueryRepository.bulkDeActive(noticeIds);
    }
}
