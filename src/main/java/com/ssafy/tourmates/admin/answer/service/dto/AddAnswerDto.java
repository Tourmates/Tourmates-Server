package com.ssafy.tourmates.admin.answer.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddAnswerDto {

    private String content;

    @Builder
    public AddAnswerDto(String content) {
        this.content = content;
    }
}
