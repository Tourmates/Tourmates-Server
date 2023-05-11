package com.ssafy.tourmates.client.hotplace.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.tourmates.client.hotplace.QHotplaceComment.hotplaceComment;

@Repository
public class HotplaceCommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HotplaceCommentQueryRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

}
