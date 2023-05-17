package com.ssafy.tourmates.client.question.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface QuestionQueryService {
}
