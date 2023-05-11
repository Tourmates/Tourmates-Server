package com.ssafy.tourmates.notice.service;

import com.ssafy.tourmates.controller.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.notice.repository.dto.NoticeSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface NoticeQueryService {

    List<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable);

    List<NoticeResponse> searchPinNotices();
}
