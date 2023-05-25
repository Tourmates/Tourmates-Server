package com.ssafy.tourmates.admin.api.dto.trend.reseponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TopTrendResponse {

    private String time;
    private List<TrendResponse> teenager;
    private List<TrendResponse> twenty;
    private List<TrendResponse> thirty;
    private List<TrendResponse> male;
    private List<TrendResponse> female;

    @Builder
    public TopTrendResponse(String time, List<TrendResponse> teenager, List<TrendResponse> twenty, List<TrendResponse> thirty, List<TrendResponse> male, List<TrendResponse> female) {
        this.time = time;
        this.teenager = teenager;
        this.twenty = twenty;
        this.thirty = thirty;
        this.male = male;
        this.female = female;
    }
}
