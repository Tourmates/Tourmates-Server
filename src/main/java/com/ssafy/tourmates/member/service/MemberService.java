package com.ssafy.tourmates.member.service;

import com.ssafy.tourmates.member.service.dto.JoinMemberDto;

import javax.transaction.Transactional;

@Transactional
public interface MemberService {

    Long joinMember(JoinMemberDto dto);
}
