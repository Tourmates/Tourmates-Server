package com.ssafy.tourmates.client.question.service.dto;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Builder;
import lombok.Data;

@Data
public class AddQuestionDto {

    private QuestionType type;
    private String title;
    private String content;
    private String password;

    @Builder
    public AddQuestionDto(QuestionType type, String title, String content, String password) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}
