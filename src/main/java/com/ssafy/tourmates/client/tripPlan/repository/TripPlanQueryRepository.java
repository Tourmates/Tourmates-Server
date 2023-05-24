package com.ssafy.tourmates.client.tripPlan.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.client.api.dto.tripplan.response.DetailPlanDataResponse;
import com.ssafy.tourmates.client.api.dto.tripplan.response.DetailPlanResponse;
import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanResponse;
import com.ssafy.tourmates.client.tripPlan.repository.dto.PlanSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static com.ssafy.tourmates.admin.attraction.QAttractionInfo.attractionInfo;
import static com.ssafy.tourmates.client.member.Active.ACTIVE;
import static com.ssafy.tourmates.client.member.QMember.member;
import static com.ssafy.tourmates.client.tripPlan.QDetailTripPlan.detailTripPlan;
import static com.ssafy.tourmates.client.tripPlan.QTripPlan.tripPlan;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class TripPlanQueryRepository {

    private final JPAQueryFactory queryFactory;

    public TripPlanQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<PlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(tripPlan.id)
                .from(tripPlan)
                .join(tripPlan.member, member)
                .where(
                        tripPlan.parentTripPlanId.isNull(),
                        tripPlan.active.eq(ACTIVE),
                        isTitle(condition.getTitle()),
                        isNickname(condition.getNickname())
                )
                .orderBy(tripPlan.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(constructor(PlanResponse.class,
                        tripPlan.id,
                        tripPlan.title,
                        tripPlan.member.nickname,
                        tripPlan.hit,
                        tripPlan.createdDate))
                .from(tripPlan)
                .join(tripPlan.member, member)
                .where(tripPlan.id.in(ids))
                .orderBy(tripPlan.createdDate.desc())
                .fetch();
    }

    public long totalCount() {
        return queryFactory
                .select(tripPlan.id)
                .from(tripPlan)
                .where(tripPlan.active.eq(ACTIVE))
                .fetch()
                .size();
    }

    public DetailPlanResponse searchById(Long tripPlanId) {
        DetailPlanResponse response = queryFactory
                .select(constructor(DetailPlanResponse.class,
                        Expressions.asNumber(tripPlanId),
                        tripPlan.title,
                        tripPlan.member.nickname,
                        tripPlan.createdDate))
                .from(tripPlan)
                .join(tripPlan.member, member)
                .where(tripPlan.id.eq(tripPlanId))
                .fetchOne();

        if (response == null) {
            return null;
        }

        List<DetailPlanDataResponse> attractions = queryFactory
                .select(constructor(DetailPlanDataResponse.class,
                        detailTripPlan.attractionInfo.title,
                        detailTripPlan.attractionInfo.addr1,
                        detailTripPlan.attractionInfo.zipcode,
                        detailTripPlan.attractionInfo.firstImage,
                        detailTripPlan.attractionInfo.latitude,
                        detailTripPlan.attractionInfo.longitude))
                .from(detailTripPlan)
                .join(detailTripPlan.attractionInfo, attractionInfo)
                .where(detailTripPlan.tripPlan.id.eq(tripPlanId))
                .fetch();

        response.setPlans(attractions);
        return response;
    }

    private BooleanExpression isTitle(String title) {
        return hasText(title) ? tripPlan.title.like("%" + title + "%") : null;
    }

    private BooleanExpression isNickname(String nickname) {
        return hasText(nickname) ? tripPlan.member.nickname.like("%" + nickname + "%") : null;
    }
}
