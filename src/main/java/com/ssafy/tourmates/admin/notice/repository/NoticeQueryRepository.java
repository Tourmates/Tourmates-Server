package com.ssafy.tourmates.admin.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.api.dto.notice.response.EditNoticeResponse;
import com.ssafy.tourmates.admin.controller.dto.notice.response.AdminNoticeResponse;
import com.ssafy.tourmates.admin.notice.repository.dto.NoticeSearchCondition;
import com.ssafy.tourmates.admin.api.dto.notice.response.DetailNoticeResponse;
import com.ssafy.tourmates.admin.api.dto.notice.response.NoticeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.admin.admin.QAdmin.*;
import static com.ssafy.tourmates.admin.notice.QNotice.*;
import static com.ssafy.tourmates.client.member.Active.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class NoticeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(notice.id)
                .from(notice)
                .where(
                        notice.pin.eq("0"),
                        notice.active.eq(ACTIVE),
                        isKeyword(condition.getKeyword())
                )
                .orderBy(notice.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();



        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(NoticeResponse.class,
                        notice.id,
                        notice.pin,
                        notice.title,
                        notice.createdDate))
                .from(notice)
                .where(notice.id.in(ids))
                .orderBy(notice.createdDate.desc())
                .fetch();
    }

    public long totalCount(NoticeSearchCondition condition) {
        return queryFactory
                .select(notice.id)
                .from(notice)
                .where(
                        notice.pin.eq("0"),
                        notice.active.eq(ACTIVE),
                        isKeyword(condition.getKeyword())
                )
                .fetch()
                .size();
    }

    public List<NoticeResponse> searchPinNotices() {
        return queryFactory
                .select(Projections.constructor(NoticeResponse.class,
                        notice.id,
                        notice.pin,
                        notice.title,
                        notice.createdDate))
                .from(notice)
                .where(notice.pin.eq("1"))
                .orderBy(notice.createdDate.desc())
                .fetch();
    }

    public DetailNoticeResponse searchNotice(Long noticeId) {
        return queryFactory
                .select(Projections.constructor(DetailNoticeResponse.class,
                        notice.id,
                        notice.title,
                        notice.content,
                        notice.createdDate))
                .from(notice)
                .where(notice.id.eq(noticeId))
                .fetchOne();
    }

    public EditNoticeResponse searchEditNotice(Long noticeId) {
        return queryFactory
                .select(Projections.constructor(EditNoticeResponse.class,
                        notice.id,
                        notice.title,
                        notice.content,
                        notice.pin,
                        notice.createdDate))
                .from(notice)
                .where(notice.id.eq(noticeId))
                .fetchOne();
    }

    public List<AdminNoticeResponse> searchAdminNotices() {
        return queryFactory
                .select(Projections.constructor(AdminNoticeResponse.class,
                        notice.id,
                        notice.title,
                        notice.admin.name,
                        notice.createdDate,
                        notice.active))
                .from(notice)
                .join(notice.admin, admin)
                .fetch();
    }

    public Long bulkDeActive(List<Long> noticeIds) {
        return queryFactory
                .update(notice)
                .where(notice.id.in(noticeIds))
                .set(notice.active, DEACTIVE)
                .execute();
    }

    private BooleanExpression isKeyword(String keyword) {
        return hasText(keyword) ? notice.title.like("%" + keyword + "%")
                .or(notice.content.like("%" + keyword + "%")) : null;
    }
}
