package com.ssafy.tourmates.admin.attraction.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.attraction.repository.AttractionRepositoryCustom;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.controller.dto.attraction.response.AttractionResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.tourmates.admin.attraction.QAttractionInfo.attractionInfo;

@Repository
public class AttractionQueryRepository implements AttractionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AttractionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition) {
        return queryFactory
                .select(Projections.fields(AttractionResponse.class))
                .from(attractionInfo)
                .where(
                        attractionInfo.sido.code.eq(condition.getSidoCode()),
                        attractionInfo.gugun.code.eq(condition.getGugunCode())
                )
                .fetch();
    }
}
