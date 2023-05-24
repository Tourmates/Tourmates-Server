package com.ssafy.tourmates.admin.trend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.api.dto.trend.reseponse.TrendResponse;
import com.ssafy.tourmates.admin.trend.TopTrend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ssafy.tourmates.admin.api.dto.trend.reseponse.Change.*;
import static com.ssafy.tourmates.admin.attraction.QAttractionInfo.*;
import static com.ssafy.tourmates.admin.trend.QTopTrend.*;
import static com.ssafy.tourmates.admin.trend.QTrend.*;
import static com.ssafy.tourmates.admin.trend.TrendType.*;

@Repository
@Slf4j
public class TrendQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TrendQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Integer> searchTop10Teenager() {
        List<Integer> contents = queryFactory
                .select(trend.attractionInfo.id)
                .from(trend)
                .orderBy(trend.teenage.desc())
                .limit(10)
                .offset(0)
                .fetch();
        return contents;
    }

    public List<Integer> searchTop10Twenty() {
        List<Integer> contents = queryFactory
                .select(trend.attractionInfo.id)
                .from(trend)
                .orderBy(trend.twenty.desc())
                .limit(10)
                .offset(0)
                .fetch();
        return contents;
    }

    public List<Integer> searchTop10Thirty() {
        List<Integer> contents = queryFactory
                .select(trend.attractionInfo.id)
                .from(trend)
                .orderBy(trend.thirty.desc())
                .limit(10)
                .offset(0)
                .fetch();
        return contents;
    }

    public List<Integer> searchTop10Male() {
        List<Integer> contents = queryFactory
                .select(trend.attractionInfo.id)
                .from(trend)
                .orderBy(trend.male.desc())
                .limit(10)
                .offset(0)
                .fetch();
        return contents;
    }

    public List<Integer> searchTop10Female() {
        List<Integer> contents = queryFactory
                .select(trend.attractionInfo.id)
                .from(trend)
                .orderBy(trend.female.desc())
                .limit(10)
                .offset(0)
                .fetch();
        return contents;
    }

    public List<TrendResponse> searchTeenageTopTrend() {
        List<TopTrend> current = queryFactory
                .selectFrom(topTrend)
                .join(topTrend.attractionInfo, attractionInfo)
                .where(topTrend.type.eq(TEENAGE))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(0)
                .limit(10)
                .fetch();

        List<Integer> before = queryFactory
                .select(topTrend.attractionInfo.id)
                .from(topTrend)
                .where(topTrend.type.eq(TEENAGE))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(10)
                .limit(10)
                .fetch();
        Collections.reverse(current);
        Collections.reverse(before);
        List<TrendResponse> responses = new ArrayList<>();
        for (TopTrend topTrend : current) {
            TrendResponse response = null;
            int index = before.indexOf(topTrend.getAttractionInfo().getId()) + 1;
            if (index == 0) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(NEW)
                        .build();
            } else if (index == topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(EQUAL)
                        .build();
            } else if (index > topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(UP)
                        .build();
            } else {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(DOWN)
                        .build();
            }
            responses.add(response);
        }
        return responses;
    }

    public List<TrendResponse> searchTwentyTopTrend() {
        List<TopTrend> current = queryFactory
                .selectFrom(topTrend)
                .join(topTrend.attractionInfo, attractionInfo)
                .where(topTrend.type.eq(TWENTY))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(0)
                .limit(10)
                .fetch();

        List<Integer> before = queryFactory
                .select(topTrend.attractionInfo.id)
                .from(topTrend)
                .where(topTrend.type.eq(TWENTY))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(10)
                .limit(10)
                .fetch();
        Collections.reverse(current);
        Collections.reverse(before);
        List<TrendResponse> responses = new ArrayList<>();
        for (TopTrend topTrend : current) {
            TrendResponse response = null;
            int index = before.indexOf(topTrend.getAttractionInfo().getId()) + 1;
            if (index == 0) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(NEW)
                        .build();
            } else if (index == topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(EQUAL)
                        .build();
            } else if (index > topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(UP)
                        .build();
            } else {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(DOWN)
                        .build();
            }
            responses.add(response);
        }
        return responses;
    }

    public List<TrendResponse> searchThirtyTopTrend() {
        List<TopTrend> current = queryFactory
                .selectFrom(topTrend)
                .join(topTrend.attractionInfo, attractionInfo)
                .where(topTrend.type.eq(THIRTY))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(0)
                .limit(10)
                .fetch();

        List<Integer> before = queryFactory
                .select(topTrend.attractionInfo.id)
                .from(topTrend)
                .where(topTrend.type.eq(THIRTY))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(10)
                .limit(10)
                .fetch();
        Collections.reverse(current);
        Collections.reverse(before);
        List<TrendResponse> responses = new ArrayList<>();
        for (TopTrend topTrend : current) {
            TrendResponse response = null;
            int index = before.indexOf(topTrend.getAttractionInfo().getId()) + 1;
            if (index == 0) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(NEW)
                        .build();
            } else if (index == topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(EQUAL)
                        .build();
            } else if (index > topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(UP)
                        .build();
            } else {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(DOWN)
                        .build();
            }
            responses.add(response);
        }
        return responses;
    }

    public List<TrendResponse> searchMaleTopTrend() {
        List<TopTrend> current = queryFactory
                .selectFrom(topTrend)
                .join(topTrend.attractionInfo, attractionInfo)
                .where(topTrend.type.eq(MALE))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(0)
                .limit(10)
                .fetch();

        List<Integer> before = queryFactory
                .select(topTrend.attractionInfo.id)
                .from(topTrend)
                .where(topTrend.type.eq(MALE))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(10)
                .limit(10)
                .fetch();
        Collections.reverse(current);
        Collections.reverse(before);
        List<TrendResponse> responses = new ArrayList<>();
        for (TopTrend topTrend : current) {
            TrendResponse response = null;
            int index = before.indexOf(topTrend.getAttractionInfo().getId()) + 1;
            if (index == 0) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(NEW)
                        .build();
            } else if (index == topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(EQUAL)
                        .build();
            } else if (index > topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(UP)
                        .build();
            } else {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(DOWN)
                        .build();
            }
            responses.add(response);
        }
        return responses;
    }

    public List<TrendResponse> searchFemaleTopTrend() {
        List<TopTrend> current = queryFactory
                .selectFrom(topTrend)
                .join(topTrend.attractionInfo, attractionInfo)
                .where(topTrend.type.eq(FEMALE))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(0)
                .limit(10)
                .fetch();

        List<Integer> before = queryFactory
                .select(topTrend.attractionInfo.id)
                .from(topTrend)
                .where(topTrend.type.eq(FEMALE))
                .orderBy(topTrend.createdDate.desc(), topTrend.rank.desc())
                .offset(10)
                .limit(10)
                .fetch();

        Collections.reverse(current);
        Collections.reverse(before);
        log.debug("before={}", before);
        List<TrendResponse> responses = new ArrayList<>();
        for (TopTrend topTrend : current) {
            TrendResponse response = null;
            int index = before.indexOf(topTrend.getAttractionInfo().getId()) + 1;
            log.debug("topTrand={}", topTrend.getAttractionInfo().getId());
            log.debug("index={}", index);
            log.debug("rank={}", topTrend.getRank());
            if (index == 0) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(NEW)
                        .build();
            } else if (index == topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(EQUAL)
                        .build();
            } else if (index > topTrend.getRank()) {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(UP)
                        .build();
            } else {
                response = TrendResponse.builder()
                        .title(topTrend.getAttractionInfo().getTitle())
                        .rank(topTrend.getRank())
                        .change(DOWN)
                        .build();
            }
            responses.add(response);
        }
        return responses;
    }
}
