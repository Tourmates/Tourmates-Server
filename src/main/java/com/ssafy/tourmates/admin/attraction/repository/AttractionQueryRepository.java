package com.ssafy.tourmates.admin.attraction.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionResponse;
import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionSearchResponse;
import com.ssafy.tourmates.admin.api.dto.attraction.response.GugunResponse;
import com.ssafy.tourmates.admin.api.dto.attraction.response.SidoResponse;
import com.ssafy.tourmates.admin.attraction.QGugun;
import com.ssafy.tourmates.admin.attraction.QSido;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.querydsl.core.types.Projections.*;
import static com.ssafy.tourmates.admin.attraction.QAttractionInfo.*;
import static com.ssafy.tourmates.admin.attraction.QGugun.*;
import static com.ssafy.tourmates.admin.attraction.QSido.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class AttractionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AttractionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition) {
        return queryFactory
                .select(constructor(AttractionResponse.class,
                        attractionInfo.id,
                        attractionInfo.title,
                        attractionInfo.addr1,
                        attractionInfo.zipcode,
                        attractionInfo.tel,
                        attractionInfo.firstImage,
                        attractionInfo.latitude,
                        attractionInfo.longitude))
                .from(attractionInfo)
                .where(
                        attractionInfo.sido.code.eq(condition.getSidoCode()),
                        attractionInfo.gugun.code.eq(condition.getGugunCode()),
                        isKeyword(condition.getKeyword()),
                        isContentTypeId(condition.getContentTypeId())
                )
                .fetch();
    }

    public List<SidoResponse> searchSido() {
        return queryFactory
                .select(constructor(SidoResponse.class,
                        sido.code,
                        sido.name))
                .from(sido)
                .orderBy(sido.code.asc())
                .fetch();
    }

    public List<GugunResponse> searchGugun(Integer sidoCode) {
        return queryFactory
                .select(constructor(GugunResponse.class,
                        gugun.code,
                        gugun.name))
                .from(gugun)
                .where(gugun.sido.code.eq(sidoCode))
                .orderBy(gugun.code.asc())
                .fetch();
    }

    public List<AttractionSearchResponse> searchAllTitle() {
        return queryFactory
                .select(constructor(AttractionSearchResponse.class,
                        attractionInfo.id,
                        attractionInfo.title))
                .from(attractionInfo)
                .orderBy(attractionInfo.title.asc())
                .fetch();
    }

    private BooleanExpression isKeyword(String keyword) {
        return hasText(keyword) ? attractionInfo.title.like("%" + keyword + "%") : null;
    }

    private BooleanExpression isContentTypeId(Integer contentTypeId) {
        return contentTypeId > 0 ? attractionInfo.contentTypeId.eq(contentTypeId) : null;
    }
}
