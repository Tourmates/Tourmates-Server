package com.ssafy.tourmates.client.hashtag.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.client.hashtag.Hashtag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.tourmates.client.hashtag.QHashtag.*;

@Repository
public class HashtagQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HashtagQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<String> searchTagNames(List<String> tagNames) {
        return queryFactory
                .select(hashtag.tagName)
                .from(hashtag)
                .where(hashtag.tagName.in(tagNames))
                .fetch();
    }

    public List<Long> searchTagIds(List<String> tagNames) {
        return queryFactory
                .select(hashtag.id)
                .from(hashtag)
                .where(hashtag.tagName.in(tagNames))
                .fetch();
    }

    public List<Hashtag> searchByIds(List<Long> ids) {
        return queryFactory
                .select(hashtag)
                .from(hashtag)
                .where(hashtag.id.in(ids))
                .fetch();
    }
}
