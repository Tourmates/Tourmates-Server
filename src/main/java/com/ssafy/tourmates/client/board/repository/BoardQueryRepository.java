package com.ssafy.tourmates.client.board.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.board.repository.dto.Sort;
import com.ssafy.tourmates.client.controller.dto.board.response.BoardResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.board.QBoard.*;

@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BoardQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(board.id)
                .from(board)
                .where(
                        isKeyword(condition.getKeyword())
                )
                .orderBy(
                        sorted(condition.getSort())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.fields(BoardResponse.class,
                        board.id.as("boardId"),
                        board.title,
                        board.hit,
                        board.createdDate))
                .from(board)
                .where(board.id.in(ids))
                .orderBy(sorted(condition.getSort()))
                .fetch();
    }

    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? board.title.like("%" + keyword + "%")
                .or(board.content.like("%" + keyword + "%")) : null;
    }

    private OrderSpecifier sorted(Sort sort) {
        return sort == Sort.HIT ? board.hit.desc() : board.createdDate.desc();
    }
}
