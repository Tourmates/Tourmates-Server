package com.ssafy.tourmates.client.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.ssafy.tourmates.client.member.QMember.member;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Boolean existLoginId(String loginId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchFirst();
        return fetchOne != null;
    }

    public Boolean existEmail(String email) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(email))
                .fetchFirst();
        return fetchOne != null;
    }

    public Boolean existTel(String tel) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(member)
                .where(member.tel.eq(tel))
                .fetchFirst();
        return fetchOne != null;
    }

    public Boolean existNickname(String nickname) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(member)
                .where(member.nickname.eq(nickname))
                .fetchFirst();
        return fetchOne != null;
    }
}
