package com.ssafy.tourmates.client.hotplace.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class HotPlaceCommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HotPlaceCommentQueryRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

}
