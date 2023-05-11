package com.ssafy.tourmates.admin.notice.validator;

import com.ssafy.tourmates.admin.notice.Notice;
import com.ssafy.tourmates.admin.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class NoticeValidator {

    private final NoticeRepository noticeRepository;

    public Notice findById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}

