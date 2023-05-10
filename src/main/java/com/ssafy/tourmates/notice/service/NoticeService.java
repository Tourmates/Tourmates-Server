package com.ssafy.tourmates.notice.service;

import com.ssafy.tourmates.notice.service.dto.AddNoticeDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NoticeService {

    Long registerNotice(String loginId, AddNoticeDto dto);
}
