package com.ssafy.tourmates.client.member.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DuplicateCheckService {

    Boolean existLoginId(String loginId);

    Boolean existEmail(String email);

    Boolean existTel(String tel);

    Boolean existNickname(String nickname);
}
