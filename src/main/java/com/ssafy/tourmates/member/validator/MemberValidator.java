package com.ssafy.tourmates.member.validator;

import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    public Member findByTel(String tel) {
        return memberRepository.findByTel(tel)
                .orElseThrow(NoSuchElementException::new);
    }
}
