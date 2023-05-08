package com.ssafy.tourmates.member.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberQueryService {

    String forgotLoginIdByEmail(String email);

    String forgotLoginIdByTel(String tel);
}
