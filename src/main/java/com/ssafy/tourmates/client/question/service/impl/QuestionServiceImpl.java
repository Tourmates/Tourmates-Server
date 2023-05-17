package com.ssafy.tourmates.client.question.service.impl;

import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.client.question.Question;
import com.ssafy.tourmates.client.question.repository.QuestionRepository;
import com.ssafy.tourmates.client.question.service.QuestionService;
import com.ssafy.tourmates.client.question.service.dto.AddQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.tourmates.client.member.Active.*;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberValidator memberValidator;

    @Override
    public Long registerQuestion(String loginId, AddQuestionDto dto) {
        Member member = memberValidator.findByLoginId(loginId);

        Question question = Question.builder()
                .type(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .password(dto.getPassword())
                .active(ACTIVE)
                .member(member)
                .build();

        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }
}
