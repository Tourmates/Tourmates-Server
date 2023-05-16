package com.ssafy.tourmates.client.member.service;

import com.ssafy.tourmates.admin.controller.dto.member.response.MemberResponse;
import com.ssafy.tourmates.jwt.TokenInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface MemberQueryService {

    TokenInfo login(String loginId, String loginPw);

    String forgotLoginIdByEmail(String email);

    String forgotLoginIdByTel(String tel);

    String forgotLoginPwByEmail(String loginId, String email);

    String forgotLoginPwByTel(String loginId, String tel);

    List<MemberResponse> searchMembers();
}
