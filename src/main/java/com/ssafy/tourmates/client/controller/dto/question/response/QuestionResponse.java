package com.ssafy.tourmates.client.controller.dto.question.response;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.util.StringUtils.*;

@Data
public class QuestionResponse {

    private Long questionId;
    private QuestionType type;
    private Boolean anonymous;
    private String createdDate;

    public QuestionResponse(Long questionId, QuestionType type, String password, LocalDateTime createdDate) {
        this.questionId = questionId;
        this.type = type;
        this.anonymous = hasText(password);
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
    }
}
