package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.controller.dto.notice.request.AddNoticeRequest;
import com.ssafy.tourmates.controller.dto.notice.request.EditNoticeRequest;
import com.ssafy.tourmates.controller.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.jwt.SecurityUtil;
import com.ssafy.tourmates.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.notice.service.NoticeQueryService;
import com.ssafy.tourmates.notice.service.NoticeService;
import com.ssafy.tourmates.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.notice.service.dto.EditNoticeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notices")
@Api(tags = {"공지사항"})
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeQueryService noticeQueryService;

    @ApiOperation(value = "공지사항 조회")
    @GetMapping
    public ResultPage<List<NoticeResponse>> searchNotices(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        NoticeSearchCondition condition = NoticeSearchCondition.builder()
                .keyword(keyword)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<NoticeResponse> responses = noticeQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

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

    @ApiOperation(value = "공지사항 수정")
    @PostMapping("/{noticeId}/edit")
    public Long editNotice(@PathVariable Long noticeId, EditNoticeRequest request) {
        EditNoticeDto dto = EditNoticeDto.builder()
                .pin(request.getPin())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Long editNoticeId = noticeService.editNotice(noticeId, dto);
        log.debug("request={}", request);
        log.debug("editNoticeId={}", editNoticeId);
        return editNoticeId;
    }

    @ApiOperation(value = "공지사항 삭제")
    @PostMapping("/{noticeId}/remove")
    public int removeNotice(@PathVariable Long noticeId) {
        Long removedNoticeId = noticeService.removeNotice(noticeId);
        log.debug("removedNoticeId={}", removedNoticeId);
        return 1;
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}
