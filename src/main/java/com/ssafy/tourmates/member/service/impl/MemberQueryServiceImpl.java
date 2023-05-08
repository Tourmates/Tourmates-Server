package com.ssafy.tourmates.member.service.impl;

import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.service.MemberQueryService;
import com.ssafy.tourmates.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberValidator memberValidator;

    @Override
    public String forgotLoginIdByEmail(String email) {
        Member findMember = memberValidator.findByEmail(email);
        return findMember.getLoginId();
    }

    @Override
    public String forgotLoginIdByTel(String tel) {
        Member findMember = memberValidator.findByTel(tel);
        return findMember.getLoginId();
    }
}
