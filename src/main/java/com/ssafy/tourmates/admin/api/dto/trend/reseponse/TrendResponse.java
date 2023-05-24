package com.ssafy.tourmates.admin.api.dto.trend.reseponse;

import lombok.Builder;
import lombok.Data;

@Data
public class TrendResponse {

    private String title;
    private Integer rank;
    private Change change;

    @Builder
    public TrendResponse(String title, Integer rank, Change change) {
        this.title = title;
        this.rank = rank;
        this.change = change;
    }
}
