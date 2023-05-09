package com.ssafy.tourmates.hotplace.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.hotplace.repository.HotPlaceRepositoryCustom;

import javax.persistence.EntityManager;

public class HotPlaceRepositoryImpl implements HotPlaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HotPlaceRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
