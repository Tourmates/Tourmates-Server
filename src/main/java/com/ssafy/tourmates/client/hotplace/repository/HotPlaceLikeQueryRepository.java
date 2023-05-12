package com.ssafy.tourmates.client.hotplace.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class HotPlaceLikeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HotPlaceLikeQueryRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
}
