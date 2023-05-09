package com.ssafy.tourmates.member.service;

import com.ssafy.tourmates.member.service.dto.EditLoginPwDto;
import com.ssafy.tourmates.member.service.dto.JoinMemberDto;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long joinMember(JoinMemberDto dto);

    Long editLoginPw(String loginId, EditLoginPwDto dto);

    Long editEmail(String loginId, String email);
}
