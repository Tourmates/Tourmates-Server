package com.ssafy.tourmates.admin.api;

import com.ssafy.tourmates.admin.api.dto.notice.request.EditNoticeRequest;
import com.ssafy.tourmates.admin.api.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.EditNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
import com.ssafy.tourmates.admin.notice.service.NoticeService;
import com.ssafy.tourmates.admin.notice.service.dto.EditNoticeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notices")
@Api(tags = {"공지사항"})
public class NoticeApiController {

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
        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 10);
        List<NoticeResponse> pinResponses = noticeQueryService.searchPinNotices();
        List<NoticeResponse> responses = noticeQueryService.searchByCondition(condition, pageRequest);
        List<NoticeResponse> res = new ArrayList<>();
        res.addAll(pinResponses);
        res.addAll(responses);
        return new ResultPage<>(res);
    }

    @GetMapping("/totalCount")
    public Long paging(
            @RequestParam(defaultValue = "") String keyword
    ) {
        NoticeSearchCondition condition = NoticeSearchCondition.builder()
                .keyword(keyword)
                .build();
        Long totalCount = noticeQueryService.getTotalCount(condition);
        log.debug("totalCount={}", totalCount);
        return totalCount;
    }

    @ApiOperation(value = "공지사항 상세조회")
    @GetMapping("/{noticeId}")
    public Result<DetailNoticeResponse> searchNotice(@PathVariable Long noticeId) {
        DetailNoticeResponse response = noticeQueryService.searchNotice(noticeId);
        return new Result<>(response);
    }

    @ApiOperation(value = "공지사항 수정 조회")
    @GetMapping("/{noticeId}/edit")
    public Result<EditNoticeResponse> editNotice(@PathVariable Long noticeId) {
        EditNoticeResponse response = noticeQueryService.searchEditNotice(noticeId);
        return new Result<>(response);
    }

    @ApiOperation(value = "공지사항 수정")
    @PostMapping("/{noticeId}/edit")
    public Long editNotice(@PathVariable Long noticeId, @RequestBody EditNoticeRequest request) {
        log.debug("request={}", request);
        EditNoticeDto dto = EditNoticeDto.builder()
                .pin(request.getPin().equals("true") ? "1" : "0")
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Long editNoticeId = noticeService.editNotice(noticeId, dto);
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
    }
}
