package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.question.request.AddQuestionRequest;
import com.ssafy.tourmates.client.api.dto.question.response.DetailQuestionResponse;
import com.ssafy.tourmates.client.api.dto.question.response.QuestionResponse;
import com.ssafy.tourmates.client.question.QuestionType;
import com.ssafy.tourmates.client.question.repository.dto.QuestionSearchCondition;
import com.ssafy.tourmates.client.question.service.QuestionQueryService;
import com.ssafy.tourmates.client.question.service.QuestionService;
import com.ssafy.tourmates.client.question.service.dto.AddQuestionDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/qna")
@Api(tags = {"질문"})
public class QuestionApiController {

    private final QuestionService questionService;
    private final QuestionQueryService questionQueryService;

    @ApiOperation(value = "질문 조회")
    @GetMapping
    public Result<List<QuestionResponse>> searchQuestions(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(required = false) QuestionType type,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        QuestionSearchCondition condition = QuestionSearchCondition.builder()
                .title(title)
                .type(type)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 20);
        List<QuestionResponse> responses = questionQueryService.searchByCondition(condition, pageRequest);
        log.debug("responses size={}", responses.size());
        log.debug("condition={}", condition);
        log.debug("pageNumber={}", pageNumber);
        return new Result<>(responses);
    }

    @ApiOperation(value = "질문 총 갯수 조회")
    @GetMapping("/totalCount")
    public Long searchTotalCount(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(required = false) QuestionType type
    ) {
        QuestionSearchCondition condition = QuestionSearchCondition.builder()
                .title(title)
                .type(type)
                .build();
        Long totalCount = questionQueryService.totalCount(condition);
        log.debug("totalCount={}", totalCount);
        return totalCount;
    }

    @ApiOperation(value = "질문 등록")
    @PostMapping("/register")
    public Long registerQuestion(@Valid @RequestBody AddQuestionRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        AddQuestionDto dto = AddQuestionDto.builder()
                .type(request.getType())
                .title(request.getTitle())
                .content(request.getContent())
                .password(request.getPassword())
                .build();

        Long questionId = questionService.registerQuestion(loginId, dto);
        log.debug("questionId={}", questionId);
        return questionId;
    }

    @ApiOperation(value = "질문 상세 조회")
    @GetMapping("/{questionId}")
    public Result<DetailQuestionResponse> searchQuestion(
            @PathVariable Long questionId
    ) {
        DetailQuestionResponse response = questionQueryService.searchQuestion(questionId);
        log.debug("response={}", response);
        return new Result<>(response);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
