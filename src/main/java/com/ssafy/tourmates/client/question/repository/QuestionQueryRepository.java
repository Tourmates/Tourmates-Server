package com.ssafy.tourmates.client.question.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.client.controller.dto.question.response.QuestionResponse;
import com.ssafy.tourmates.client.question.QuestionType;
import com.ssafy.tourmates.client.question.repository.dto.QuestionSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.question.QQuestion.*;
import static org.springframework.util.StringUtils.*;

@Repository
public class QuestionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QuestionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<QuestionResponse> searchByCondition(QuestionSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(question.id)
                .from(question)
                .where(
                        isType(condition.getType()),
                        likeTitle(condition.getTitle())
                )
                .orderBy(question.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(QuestionResponse.class,
                        question.id,
                        question.type,
                        question.password,
                        question.createdDate))
                .from(question)
                .where(question.id.in((ids)))
                .orderBy(question.createdDate.desc())
                .fetch();
    }

    private BooleanExpression isType(QuestionType type) {
        return type != null ? question.type.eq(type) : null;
    }

    private BooleanExpression likeTitle(String title) {
        return hasText(title) ? question.title.like("%" + title + "%") : null;
    }
}
