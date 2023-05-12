package com.ssafy.tourmates.client.board.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardSearchCondition {

    private String keyword;
    private Sort sort;

    @Builder
    public BoardSearchCondition(String keyword, Sort sort) {
        this.keyword = keyword;
        this.sort = sort;
    }
}
