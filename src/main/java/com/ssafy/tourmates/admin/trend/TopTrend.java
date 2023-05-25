package com.ssafy.tourmates.admin.trend;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.common.domain.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class TopTrend extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "top_trend_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private TrendType type;
    @Column(name = "trend_rank", nullable = false, updatable = false)
    private int rank;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @Builder
    public TopTrend(Long id, TrendType type, int rank, AttractionInfo attractionInfo) {
        this.id = id;
        this.type = type;
        this.rank = rank;
        this.attractionInfo = attractionInfo;
    }
}
