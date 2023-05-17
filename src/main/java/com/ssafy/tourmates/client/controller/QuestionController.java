package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.controller.dto.question.request.AddQuestionRequest;
import com.ssafy.tourmates.client.question.service.QuestionService;
import com.ssafy.tourmates.client.question.service.dto.AddQuestionDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

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
}
