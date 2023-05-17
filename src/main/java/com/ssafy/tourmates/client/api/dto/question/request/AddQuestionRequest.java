package com.ssafy.tourmates.client.api.dto.question.request;

import com.ssafy.tourmates.client.question.QuestionType;
import lombok.Data;

@Data
public class AddQuestionRequest {

    private QuestionType type;
    private String title;
    private String content;
    private String password;
}
