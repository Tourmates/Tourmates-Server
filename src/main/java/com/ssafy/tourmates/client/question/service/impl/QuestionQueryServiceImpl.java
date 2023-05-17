package com.ssafy.tourmates.client.question.service.impl;

import com.ssafy.tourmates.client.api.dto.question.response.DetailQuestionResponse;
import com.ssafy.tourmates.client.api.dto.question.response.QuestionResponse;
import com.ssafy.tourmates.client.question.repository.QuestionQueryRepository;
import com.ssafy.tourmates.client.question.repository.dto.QuestionSearchCondition;
import com.ssafy.tourmates.client.question.service.QuestionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionQueryServiceImpl implements QuestionQueryService {

    private final QuestionQueryRepository questionQueryRepository;


    @Override
    public List<QuestionResponse> searchByCondition(QuestionSearchCondition condition, Pageable pageable) {
        return questionQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public Long totalCount(QuestionSearchCondition condition) {
        return questionQueryRepository.totalCount(condition);
    }

    @Override
    public DetailQuestionResponse searchQuestion(Long questionId) {
        return questionQueryRepository.searchQuestion(questionId);
    }
}
