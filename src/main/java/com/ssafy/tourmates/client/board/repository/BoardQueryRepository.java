package com.ssafy.tourmates.client.board.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.board.repository.dto.Sort;
import com.ssafy.tourmates.client.api.dto.board.response.BoardResponse;
import com.ssafy.tourmates.client.api.dto.board.response.DetailBoardResponse;
import com.ssafy.tourmates.common.domain.ContentType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.board.QBoard.*;
import static com.ssafy.tourmates.client.member.Active.*;
import static com.ssafy.tourmates.client.member.QMember.*;

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
                        board.active.eq(ACTIVE),
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
                .select(Projections.constructor(BoardResponse.class,
                        board.id,
                        Expressions.asEnum(ContentType.ATTRACTION),
                        board.title,
                        board.hit,
                        board.member.nickname,
                        board.createdDate))
                .from(board)
                .where(board.id.in(ids))
                .orderBy(sorted(condition.getSort()))
                .fetch();
    }

    public long totalCount(BoardSearchCondition condition) {
        return queryFactory
                .select(board.id)
                .from(board)
                .where(
                        board.active.eq(ACTIVE),
                        isKeyword(condition.getKeyword())
                )
                .fetch()
                .size();
    }

    public DetailBoardResponse searchBoard(Long boardId) {
        return queryFactory
                .select(Projections.constructor(DetailBoardResponse.class,
                        Expressions.asNumber(boardId).as("boardId"),
                        board.title,
                        board.content,
                        board.hit,
                        board.createdDate,
                        board.member.nickname))
                .from(board)
                .join(board.member, member)
                .where(board.id.eq(boardId))
                .fetchOne();
    }

    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? board.title.like("%" + keyword + "%")
                .or(board.content.like("%" + keyword + "%")) : null;
    }

    private OrderSpecifier sorted(Sort sort) {
        return sort == Sort.HIT ? board.hit.desc() : board.createdDate.desc();
    }
}
