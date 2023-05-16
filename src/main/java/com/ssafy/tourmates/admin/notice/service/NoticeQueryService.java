package com.ssafy.tourmates.admin.notice.service;

import com.ssafy.tourmates.admin.api.dto.notice.response.EditNoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.AdminNoticeResponse;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.api.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.NoticeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface NoticeQueryService {

    List<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable);

    List<NoticeResponse> searchPinNotices();

    Long getTotalCount(NoticeSearchCondition condition);

    DetailNoticeResponse searchNotice(Long noticeId);

    EditNoticeResponse searchEditNotice(Long noticeId);

    List<AdminNoticeResponse> searchAdminNotices();

    @Transactional
    Long bulkDeActive(List<Long> noticeIds);
}
