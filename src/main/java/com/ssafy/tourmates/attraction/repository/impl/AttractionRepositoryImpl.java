package com.ssafy.tourmates.attraction.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.attraction.repository.AttractionRepositoryCustom;

import javax.persistence.EntityManager;

public class AttractionRepositoryImpl implements AttractionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AttractionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
