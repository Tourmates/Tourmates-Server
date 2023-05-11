package com.ssafy.tourmates.admin.notice.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class NoticeSearchCondition {

    private String keyword;

    @Builder
    public NoticeSearchCondition(String keyword) {
        this.keyword = keyword;
    }
}
