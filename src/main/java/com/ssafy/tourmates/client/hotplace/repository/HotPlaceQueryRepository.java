package com.ssafy.tourmates.client.hotplace.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.client.controller.dto.hotplace.response.HotPlaceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.hotplace.QHotPlace.*;
import static com.ssafy.tourmates.member.QMember.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class HotPlaceQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HotPlaceQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(hotPlace.id)
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

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(
                        Projections.fields(HotPlaceResponse.class,
                                hotPlace.id,
                                hotPlace.tag,
                                hotPlace.title,
                                hotPlace.content,
                                hotPlace.hit,
                                hotPlace.visitedDate,
                                hotPlace.images.get(0).uploadFile.storeFileName))
                .from(hotPlace)
                .where(hotPlace.id.in(ids))
                .orderBy(hotPlace.createdDate.desc())
                .fetch();
    }

    public HotPlace searchById(Long hotPlaceId) {
        return queryFactory
                .select(hotPlace)
                .from(hotPlace)
                .join(hotPlace.member, member).fetchJoin()
                .where(hotPlace.id.eq(hotPlaceId))
                .fetchOne();
    }

    private BooleanExpression isTag(ContentType tag) {
        return tag != null ? hotPlace.tag.eq(tag) : null;
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? hotPlace.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isContent(String content) {
        return hasText(content) ? hotPlace.content.eq("%" + content + "%") : null;
    }
}
