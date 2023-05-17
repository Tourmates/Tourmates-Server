package com.ssafy.tourmates.admin.answer.service;

import com.ssafy.tourmates.admin.answer.service.dto.AddAnswerDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AnswerService {

    Long registerAnswer(String loginId, Long questionId, AddAnswerDto dto);
}
