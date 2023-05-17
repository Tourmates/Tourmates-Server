package com.ssafy.tourmates.client.question.service;

import com.ssafy.tourmates.client.api.dto.question.response.DetailQuestionResponse;
import com.ssafy.tourmates.client.api.dto.question.response.QuestionResponse;
import com.ssafy.tourmates.client.question.repository.dto.QuestionSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface QuestionQueryService {

    List<QuestionResponse> searchByCondition(QuestionSearchCondition condition, Pageable pageable);

    Long totalCount(QuestionSearchCondition condition);

    DetailQuestionResponse searchQuestion(Long questionId);
}
