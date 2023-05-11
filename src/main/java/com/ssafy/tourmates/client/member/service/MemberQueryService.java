package com.ssafy.tourmates.client.member.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberQueryService {

    String forgotLoginIdByEmail(String email);

    String forgotLoginIdByTel(String tel);

    String forgotLoginPwByEmail(String loginId, String email);

    String forgotLoginPwByTel(String loginId, String tel);
}
