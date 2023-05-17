package com.ssafy.tourmates.client.question.service;

import com.ssafy.tourmates.client.question.service.dto.AddQuestionDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QuestionService {

    Long registerQuestion(String loginId, AddQuestionDto dto);
}
