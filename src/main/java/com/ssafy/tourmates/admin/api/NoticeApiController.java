package com.ssafy.tourmates.admin.api;

import com.ssafy.tourmates.admin.api.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.NoticeResponse;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.notice.service.NoticeQueryService;
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

    private final NoticeQueryService noticeQueryService;

    @ApiOperation(value = "공지사항 조회")
    @GetMapping
    public Result<List<NoticeResponse>> searchNotices(
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "0") Integer type,
            @RequestParam(defaultValue = "") String keyword
            ) {

        NoticeSearchCondition condition = NoticeSearchCondition.builder()
                .type(type)
                .keyword(keyword)
                .build();

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        List<NoticeResponse> responses = noticeQueryService.searchByCondition(condition, pageRequest);
        log.debug("response count={}", responses.size());
        return new Result<>(responses);
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

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
