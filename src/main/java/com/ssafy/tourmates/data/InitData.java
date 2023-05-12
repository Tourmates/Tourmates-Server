package com.ssafy.tourmates.data;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.member.Gender.*;

@Configuration
@RequiredArgsConstructor
public class InitData {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        initMember();
    }

    private void initMember() {
        List<Member> members = new ArrayList<>();
        Member member1 = Member.builder().id(1L).loginId("ssafy1").loginPw("ssafy1111!").name("임우택").email("ssafy1@ssafy.com").tel("010-1111-1111").birth("2000-01-01").gender(MALE).nickname("코코").build();
        Member member2 = Member.builder().id(2L).loginId("ssafy2").loginPw("ssafy2222!").name("정유빈").email("ssafy2@ssafy.com").tel("010-2222-2222").birth("2000-02-02").gender(FEMALE).nickname("광주").build();
        members.add(member1);
        members.add(member2);
        memberRepository.saveAll(members);
    }
}
