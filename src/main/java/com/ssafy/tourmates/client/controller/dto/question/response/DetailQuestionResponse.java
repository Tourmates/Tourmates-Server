package com.ssafy.tourmates.client.controller.dto.question.response;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Data;

@Data
public class DetailQuestionResponse {

    private QuestionType type;
    private String title;
    private String questionContent;
    private String questionCreatedDate;

    private String answerContent;
    private String answerCreatedDate;

    public DetailQuestionResponse(QuestionType type, String title, String questionContent, String questionCreatedDate, String answerContent, String answerCreatedDate) {
        this.type = type;
        this.title = title;
        this.questionContent = questionContent;
        this.questionCreatedDate = questionCreatedDate;
        this.answerContent = answerContent;
        this.answerCreatedDate = answerCreatedDate;
    }
}
