package com.ssafy.tourmates.client.hotplace.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.attraction.QAttractionInfo;
import com.ssafy.tourmates.client.api.dto.hotplace.response.EditHotPlaceResponse;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.Projections.*;
import static com.ssafy.tourmates.admin.attraction.QAttractionInfo.*;
import static com.ssafy.tourmates.client.hotplace.QHotPlace.*;
import static com.ssafy.tourmates.client.member.Active.*;
import static com.ssafy.tourmates.client.member.QMember.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class HotPlaceQueryRepository {

    private final JPAQueryFactory queryFactory;

    public HotPlaceQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<HotPlace> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(hotPlace.id)
                .from(hotPlace)
                .where(
                        hotPlace.active.eq(ACTIVE),
                        isTags(condition.getTags()),
                        isKeyword(condition.getKeyword())
                )
                .orderBy(hotPlace.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(hotPlace)
                .from(hotPlace)
                .where(hotPlace.id.in(ids))
                .orderBy(hotPlace.createdDate.desc())
                .fetch();
    }

    public List<HotPlace> searchByLoginId(String loginId, Pageable pageable) {

        System.out.println("loginId: " + loginId);
    //    System.out.println("hotplace loginId: " + hot);
        List<Long> ids = queryFactory
                .select(hotPlace.id)
                .from(hotPlace)
                .where(
                        hotPlace.active.eq(ACTIVE),
                        hotPlace.member.loginId.eq(loginId)
                )
                .orderBy(hotPlace.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(hotPlace)
                .from(hotPlace)
                .where(hotPlace.id.in(ids))
                .orderBy(hotPlace.createdDate.desc())
                .fetch();
    }

    public long totalCount(HotPlaceSearchCondition condition) {
        return queryFactory
                .select(hotPlace.id)
                .from(hotPlace)
                .where(
                        hotPlace.active.eq(ACTIVE),
                        isTags(condition.getTags()),
                        isKeyword(condition.getKeyword())
                )
                .fetch()
                .size();
    }

    public HotPlace searchById(Long hotPlaceId) {
        return queryFactory
                .select(hotPlace)
                .from(hotPlace)
                .join(hotPlace.member, member).fetchJoin()
                .join(hotPlace.attractionInfo, attractionInfo).fetchJoin()
                .where(hotPlace.id.eq(hotPlaceId))
                .fetchOne();
    }

    public EditHotPlaceResponse searchEditById(Long hotPlaceId) {
        return queryFactory
                .select(constructor(EditHotPlaceResponse.class,
                        Expressions.asNumber(hotPlaceId),
                        hotPlace.tag,
                        hotPlace.title,
                        hotPlace.visitedDate,
                        hotPlace.content,
                        hotPlace.attractionInfo.title,
                        hotPlace.attractionInfo.latitude,
                        hotPlace.attractionInfo.longitude))
                .from(hotPlace)
                .join(hotPlace.attractionInfo, attractionInfo)
                .where(hotPlace.id.eq(hotPlaceId))
                .fetchOne();
    }

    private BooleanExpression isTags(List<ContentType> tags) {
        return tags.size() > 0 ? hotPlace.tag.in(tags) : null;
    }

    private BooleanExpression isKeyword(String keyword) {
        return hasText(keyword) ? hotPlace.title.like("%" + keyword + "%")
                .or(hotPlace.content.like("%" + keyword + "%")) : null;
    }


}
