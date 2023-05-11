package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.controller.dto.notice.request.AddNoticeRequest;
import com.ssafy.tourmates.admin.controller.dto.notice.request.EditNoticeRequest;
import com.ssafy.tourmates.admin.controller.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.jwt.SecurityUtil;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
import com.ssafy.tourmates.admin.notice.service.NoticeService;
import com.ssafy.tourmates.admin.notice.service.dto.AddNoticeDto;
import com.ssafy.tourmates.admin.notice.service.dto.EditNoticeDto;
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
    public ResultPage<ResultNotice> searchNotices(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        NoticeSearchCondition condition = NoticeSearchCondition.builder()
                .keyword(keyword)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<NoticeResponse> pinResponse = noticeQueryService.searchPinNotices();
        List<NoticeResponse> responses = noticeQueryService.searchByCondition(condition, pageRequest);
        ResultNotice resultNotice = new ResultNotice(pinResponse, responses);
        return new ResultPage<>(resultNotice, pageNumber, 10);
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

    @ApiOperation(value = "공지사항 상세조회")
    @GetMapping("/{noticeId}")
    public Result<DetailNoticeResponse> searchNotice(@PathVariable Long noticeId) {
        DetailNoticeResponse response = noticeQueryService.searchNotice(noticeId);
        return new Result<>(response);
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
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }

    @Data
    @AllArgsConstructor
    static class ResultNotice {
        private List<NoticeResponse> pin;
        private List<NoticeResponse> noPin;
    }
}
