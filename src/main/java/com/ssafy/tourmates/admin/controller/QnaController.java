package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.client.question.service.QuestionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/intranet/qna")
public class QnaController {

    private final QuestionQueryService questionQueryService;

    @GetMapping
    public String qnaList() {

        return "/qnaList";
    }
}
