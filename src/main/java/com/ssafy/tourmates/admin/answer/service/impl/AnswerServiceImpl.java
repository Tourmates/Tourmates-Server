package com.ssafy.tourmates.admin.answer.service.impl;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.validator.AdminValidator;
import com.ssafy.tourmates.admin.answer.Answer;
import com.ssafy.tourmates.admin.answer.repository.AnswerRepository;
import com.ssafy.tourmates.admin.answer.service.AnswerService;
import com.ssafy.tourmates.admin.answer.service.dto.AddAnswerDto;
import com.ssafy.tourmates.client.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AdminValidator adminValidator;

    @Override
    public Long registerAnswer(String loginId, Long questionId, AddAnswerDto dto) {
        Admin admin = adminValidator.findByLoginId(loginId);

        Answer answer = Answer.builder()
                .content(dto.getContent())
                .admin(admin)
                .question(Question.builder().id(questionId).build())
                .build();

        Answer savedAnswer = answerRepository.save(answer);
        return savedAnswer.getId();
    }
}
