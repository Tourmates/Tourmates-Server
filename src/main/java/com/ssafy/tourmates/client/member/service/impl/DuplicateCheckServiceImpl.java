package com.ssafy.tourmates.client.member.service.impl;

import com.ssafy.tourmates.client.member.repository.MemberQueryRepository;
import com.ssafy.tourmates.client.member.service.DuplicateCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuplicateCheckServiceImpl implements DuplicateCheckService {

    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Boolean existLoginId(String loginId) {
        return memberQueryRepository.existLoginId(loginId);
    }

    @Override
    public Boolean existEmail(String email) {
        return memberQueryRepository.existEmail(email);
    }

    @Override
    public Boolean existTel(String tel) {
        return memberQueryRepository.existTel(tel);
    }

    @Override
    public Boolean existNickname(String nickname) {
        return memberQueryRepository.existNickname(nickname);
    }
}
