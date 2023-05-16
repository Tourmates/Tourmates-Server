package com.ssafy.tourmates.client.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tourmates.admin.controller.dto.member.response.MemberResponse;
import com.ssafy.tourmates.client.member.QMember;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.tourmates.client.member.QMember.*;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<MemberResponse> searchMembers() {
        return queryFactory
                .select(Projections.constructor(MemberResponse.class,
                        member.id,
                        member.name,
                        member.email,
                        member.birth,
                        member.nickname,
                        member.active))
                .from(member)
                .fetch();
    }
}
