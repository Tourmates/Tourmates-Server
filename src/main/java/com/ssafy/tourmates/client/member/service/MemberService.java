package com.ssafy.tourmates.client.member.service;

import com.ssafy.tourmates.client.member.service.dto.EditLoginPwDto;
import com.ssafy.tourmates.client.member.service.dto.EditMyPersonalDto;
import com.ssafy.tourmates.client.member.service.dto.JoinMemberDto;
import com.ssafy.tourmates.client.member.service.dto.MemberDetailDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long joinMember(JoinMemberDto dto);

    Long editLoginPw(String loginId, EditLoginPwDto dto);

    Long editEmail(String loginId, String email);

    Long editUsername(String loginId, String username);

    Long editTel(String loginId, String tel);

    Long editNickname(String loginId, String nickname);

    Long withdrawal(String loginId, String loginPw);

    MemberDetailDto getMemberDetail(String loginId);

    Long editMyPersonal(String loginId, EditMyPersonalDto dto);
}
