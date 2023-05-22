package com.ssafy.tourmates.client.member.service.impl;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import com.ssafy.tourmates.client.member.service.MemberService;
import com.ssafy.tourmates.client.member.service.dto.EditLoginPwDto;
import com.ssafy.tourmates.client.member.service.dto.EditMyPersonalDto;
import com.ssafy.tourmates.client.member.service.dto.JoinMemberDto;
import com.ssafy.tourmates.client.member.service.dto.MemberDetailDto;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.common.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
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
    public Long editMyPersonal(String loginId, EditMyPersonalDto dto) {

        Member findMember = memberRepository.findByLoginId(loginId).orElseThrow(NoSuchElementException::new);

        StringBuilder sb = new StringBuilder();
        sb.append(dto.getEmailId()).append("@").append(dto.getEmailDomain());
        String email = sb.toString();

        sb.setLength(0);
        sb.append(dto.getStartPhoneNumber()).append("-").append(dto.getMiddlePhoneNumber()).append("-").append(dto.getEndPhoneNumber());
        String tel = sb.toString();

        if (!findMember.getEmail().equals(email)) {
            System.out.println("--------------email 변경");
            editEmail(loginId, email);
        }
        if (!findMember.getTel().equals(tel)) {
            System.out.println("-------tel 변경");
            editTel(loginId, tel);
        }
        if (!findMember.getNickname().equals(dto.getNickname())) {
            editNickname(loginId, dto.getNickname());
        }
        if (!findMember.getUsername().equals(dto.getUsername())) {
            System.out.println("--------------username 변경");
            editUsername(loginId, dto.getUsername());
        }

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
    public Long editUsername(String loginId, String username) {
        Member findMember = memberValidator.findByLoginId(loginId);
        duplicateUsername(username);
        findMember.changeUsername(username);
        return findMember.getId();
    }

    @Override
    public Long editTel(String loginId, String tel) {
        Member findMember = memberValidator.findByLoginId(loginId);
        duplicateTel(tel);
        findMember.changeTel(tel);
        return findMember.getId();
    }

    @Override
    public Long editNickname(String loginId, String nickname) {
        Member findMember = memberValidator.findByLoginId(loginId);
        duplicateNickname(nickname);
        findMember.changeNickname(nickname);
        return findMember.getId();
    }

    @Override
    public Long withdrawal(String loginId, String loginPw) {
        Member findMember = memberValidator.findByLoginId(loginId);
        findMember.deActive(loginPw);
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

    private void duplicateUsername(String username) {
        Optional<Member> findMember = memberRepository.findByName(username);
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

    @Override
    public MemberDetailDto getMemberDetail(String loginId) {

        Member findMember = memberRepository.findByLoginId(loginId).orElseThrow(NoSuchElementException::new);

        String[] email = findMember.getEmail().split("@");
        String[] tel = findMember.getTel().split("-");

        System.out.println("######################################################username: " + findMember.getUsername());

        MemberDetailDto dto = MemberDetailDto.builder()
                .username(findMember.getUsername())
                .emailId(email[0])
                .emailDomain(email[1])
                .birth(findMember.getBirth())
                .startPhoneNumber(tel[0])
                .middlePhoneNumber(tel[1])
                .endPhoneNumber(tel[2])
                .nickname(findMember.getNickname())
                .build();

        return dto;
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
