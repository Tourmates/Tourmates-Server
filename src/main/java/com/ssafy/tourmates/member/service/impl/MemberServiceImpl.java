package com.ssafy.tourmates.member.service.impl;

import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import com.ssafy.tourmates.member.service.MemberService;
import com.ssafy.tourmates.member.service.dto.EditLoginPwDto;
import com.ssafy.tourmates.member.service.dto.JoinMemberDto;
import com.ssafy.tourmates.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    @Override
    public Long joinMember(JoinMemberDto dto) {

        duplicateLoginId(dto.getLoginId());
        duplicateEmail(dto.getEmail());
        duplicateTel(dto.getTel());
        duplicateNickname(dto.getNickname());

        Member member = createMember(dto);

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Override
    public Long editLoginPw(String loginId, EditLoginPwDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);
        findMember.changeLoginPw(dto.getCurrentLoginPw(), dto.getNewLoginPw());
        return findMember.getId();
    }

    @Override
    public Long editEmail(String loginId, String email) {
        Member findMember = memberValidator.findByLoginId(loginId);
        duplicateEmail(email);
        findMember.changeEmail(email);
        return findMember.getId();
    }

    @Override
    public Long editTel(String loginId, String tel) {
        Member findMember = memberValidator.findByLoginId(loginId);
        duplicateTel(tel);
        findMember.changeTel(tel);
        return findMember.getId();
    }

    private void duplicateLoginId(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateTel(String tel) {
        Optional<Member> findMember = memberRepository.findByTel(tel);
        if (findMember.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findByNickname(nickname);
        if (findMember.isPresent()) {
            throw new DuplicateException();
        }
    }

    private static Member createMember(JoinMemberDto dto) {
        return Member.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .email(dto.getEmail())
                .tel(dto.getTel())
                .birth(dto.getBirth())
                .gender(dto.getGender())
                .nickname(dto.getNickname())
                .build();
    }
}
