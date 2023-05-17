package com.ssafy.tourmates.client.question.repository;

import com.ssafy.tourmates.client.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
