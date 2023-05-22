package com.ssafy.tourmates.client.member.repository;

import com.ssafy.tourmates.client.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    Optional<Member> findByEmail(@Param("email") String email);

    Optional<Member> findByTel(@Param("tel") String tel);

    Optional<Member> findByNickname(@Param("nickname") String nickname);

    Optional<Member> findByName(@Param("name") String username);
}
