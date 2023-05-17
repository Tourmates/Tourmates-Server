package com.ssafy.tourmates.client.question.repository.dto;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Builder;
import lombok.Data;

@Data
public class QuestionSearchCondition {

    private QuestionType type;
    private String title;

    @Builder
    public QuestionSearchCondition(QuestionType type, String title) {
        this.type = type;
        this.title = title;
    }
}
