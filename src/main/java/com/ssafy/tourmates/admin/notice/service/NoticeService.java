package com.ssafy.tourmates.admin.notice.service;

import com.ssafy.tourmates.admin.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.admin.notice.service.dto.EditNoticeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    Long registerNotice(String loginId, AddNoticeDto dto);

    Long editNotice(Long noticeId, EditNoticeDto dto);

    Long removeNotice(Long noticeId);
}
