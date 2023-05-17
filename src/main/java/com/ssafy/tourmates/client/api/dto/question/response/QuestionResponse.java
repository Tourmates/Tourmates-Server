package com.ssafy.tourmates.client.api.dto.question.response;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.util.StringUtils.*;

@Data
public class QuestionResponse {

    private Long questionId;
    private QuestionType type;
    private String title;
    private Boolean anonymous;
    private String createdDate;

    public QuestionResponse(Long questionId, QuestionType type, String title, String password, LocalDateTime createdDate) {
        this.questionId = questionId;
        this.type = type;
        this.title = title;
        this.anonymous = hasText(password);
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
    }
}
