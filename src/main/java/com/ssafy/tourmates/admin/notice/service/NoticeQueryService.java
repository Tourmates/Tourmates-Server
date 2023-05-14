package com.ssafy.tourmates.admin.notice.service;

import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.controller.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.NoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface NoticeQueryService {

    Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable);

    List<NoticeResponse> searchPinNotices();

    DetailNoticeResponse searchNotice(Long noticeId);
}
