package com.ssafy.tourmates.client.member.service.impl;

import com.ssafy.tourmates.client.member.service.MemberQueryService;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.jwt.JwtTokenProvider;
import com.ssafy.tourmates.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberValidator memberValidator;

    @Override
    public TokenInfo login(String loginId, String loginPw) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, loginPw);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }

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
    public String forgotLoginPwByEmail(String loginId, String email) {
        Member findMember = memberValidator.findByLoginId(loginId);
        if (!findMember.getEmail().equals(email)) {
            throw new NoSuchElementException();
        }
        return findMember.getLoginPw();
    }

    @Override
    public String forgotLoginPwByTel(String loginId, String tel) {
        Member findMember = memberValidator.findByLoginId(loginId);
        if (!findMember.getTel().equals(tel)) {
            throw new NoSuchElementException();
        }
        return findMember.getLoginPw();
    }
}
