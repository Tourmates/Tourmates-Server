package com.ssafy.tourmates.admin.answer.repository;

import com.ssafy.tourmates.admin.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
