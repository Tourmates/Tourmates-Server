package com.ssafy.tourmates.member.service.impl;

import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.service.MemberQueryService;
import com.ssafy.tourmates.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    @Override
    public String forgetLoginPwByEmail(String loginId, String email) {
        Member findMember = memberValidator.findByLoginId(loginId);
        if (!findMember.getEmail().equals(email)) {
            throw new NoSuchElementException();
        }
        return findMember.getLoginPw();
    }

    @Override
    public String forgetLoginPwByTel(String loginId, String tel) {
        Member findMember = memberValidator.findByLoginId(loginId);
        if (!findMember.getTel().equals(tel)) {
            throw new NoSuchElementException();
        }
        return findMember.getLoginPw();
    }
}
