package com.ssafy.tourmates.client.api.dto.question.response;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DetailQuestionResponse {

    private QuestionType type;
    private String title;
    private String questionContent;
    private String questionCreatedDate;

    private String answerContent;
    private String answerCreatedDate;

    public DetailQuestionResponse(QuestionType type, String title, String questionContent, LocalDateTime questionCreatedDate, String answerContent, LocalDateTime answerCreatedDate) {
        this.type = type;
        this.title = title;
        this.questionContent = questionContent;
        this.questionCreatedDate = questionCreatedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.answerContent = answerContent;
        this.answerCreatedDate = answerCreatedDate != null ? answerCreatedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : null;
    }
}
