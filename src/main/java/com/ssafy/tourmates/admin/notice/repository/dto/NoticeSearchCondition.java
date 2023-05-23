package com.ssafy.tourmates.admin.notice.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class NoticeSearchCondition {

    //0: 제목, 1: 내용, 2: 제목+내용
    private Integer type;
    private String keyword;

    @Builder
    public NoticeSearchCondition(Integer type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }
}
