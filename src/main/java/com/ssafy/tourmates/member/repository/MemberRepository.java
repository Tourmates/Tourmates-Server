package com.ssafy.tourmates.member.repository;

import com.ssafy.tourmates.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
