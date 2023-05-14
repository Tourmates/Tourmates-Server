package com.ssafy.tourmates.admin.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.controller.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.NoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.admin.notice.QNotice.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class NoticeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(notice.id)
                .from(notice)
                .where(
                        notice.pin.eq("0"),
                        isKeyword(condition.getKeyword())
                )
                .orderBy(notice.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory
                .select(notice.id)
                .from(notice)
                .fetch()
                .size();

        if (CollectionUtils.isEmpty(ids)) {
            return new PageImpl<>(new ArrayList<>(), pageable, totalCount);
        }

        List<NoticeResponse> content = queryFactory
                .select(Projections.constructor(NoticeResponse.class,
                        notice.id,
                        notice.title,
                        notice.createdDate))
                .from(notice)
                .where(notice.id.in(ids))
                .orderBy(notice.createdDate.desc())
                .fetch();

        return new PageImpl<>(content, pageable, totalCount);
    }

    public List<NoticeResponse> searchPinNotices() {
        return queryFactory
                .select(Projections.constructor(NoticeResponse.class,
                        notice.id,
                        notice.title,
                        notice.createdDate))
                .from(notice)
                .where(notice.pin.eq("1"))
                .orderBy(notice.createdDate.desc())
                .fetch();
    }

    public DetailNoticeResponse searchNotice(Long noticeId) {
        return queryFactory
                .select(Projections.fields(DetailNoticeResponse.class,
                        notice.id.as("noticeId"),
                        notice.title,
                        notice.content,
                        notice.createdDate))
                .from(notice)
                .where(notice.id.eq(noticeId))
                .fetchOne();
    }

    private BooleanExpression isKeyword(String keyword) {
        return hasText(keyword) ? notice.title.like("%" + keyword + "%")
                .or(notice.content.like("%" + keyword + "%")) : null;
    }
}
