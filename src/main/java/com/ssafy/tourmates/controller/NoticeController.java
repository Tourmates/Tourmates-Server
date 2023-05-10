package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.controller.dto.notice.request.AddNoticeRequest;
import com.ssafy.tourmates.jwt.SecurityUtil;
import com.ssafy.tourmates.notice.service.NoticeService;
import com.ssafy.tourmates.notice.service.dto.AddNoticeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notices")
@Api(tags = {"공지사항"})
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "공지사항 등록")
    @PostMapping("/register")
    public Long registerNotice(AddNoticeRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        AddNoticeDto dto = AddNoticeDto.builder()
                .pin(request.getPin())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return noticeService.registerNotice(loginId, dto);
    }
}
