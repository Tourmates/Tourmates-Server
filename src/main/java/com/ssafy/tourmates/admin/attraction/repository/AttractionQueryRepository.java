package com.ssafy.tourmates.admin.attraction.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionSearchResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.ssafy.tourmates.admin.attraction.QAttractionInfo.*;

@Repository
public class AttractionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AttractionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<AttractionSearchResponse> searchAllTitle() {
        return queryFactory
                .select(Projections.constructor(AttractionSearchResponse.class,
                        attractionInfo.id,
                        attractionInfo.title))
                .from(attractionInfo)
                .orderBy(attractionInfo.title.asc())
                .fetch();
    }
}
