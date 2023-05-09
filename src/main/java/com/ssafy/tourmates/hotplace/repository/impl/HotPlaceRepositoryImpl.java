package com.ssafy.tourmates.hotplace.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.controller.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.hotplace.QHotPlace;
import com.ssafy.tourmates.hotplace.repository.HotPlaceRepositoryCustom;
import com.ssafy.tourmates.hotplace.repository.dto.HotPlaceSearchCondition;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.ssafy.tourmates.hotplace.QHotPlace.*;

public class HotPlaceRepositoryImpl implements HotPlaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HotPlaceRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        queryFactory
                .select(hotPlace)
                .from(hotPlace)
                .where(
                        isTag(condition.getTag()),
                        isTitle(condition.getTitle()),
                        isContent(condition.getContent())
                )
                .orderBy(hotPlace.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return null;
    }

    private BooleanExpression isTag(ContentType tag) {
        return null;
    }

    private BooleanExpression isTitle(String title) {
        return null;
    }

    private BooleanExpression isContent(String content) {
        return null;
    }
}
