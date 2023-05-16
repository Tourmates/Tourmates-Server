package com.ssafy.tourmates.data;

import com.ssafy.tourmates.admin.admin.Admin;
import com.ssafy.tourmates.admin.admin.repository.AdminRepository;
import com.ssafy.tourmates.admin.notice.Notice;
import com.ssafy.tourmates.admin.notice.repository.NoticeRepository;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;
import static com.ssafy.tourmates.client.member.Gender.*;

@Configuration
@RequiredArgsConstructor
public class InitData {

    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;
    private final AdminRepository adminRepository;

    @PostConstruct
    public void init() {
        initMember();
        initAdmin();
        initNotice();
    }

    private void initMember() {
        List<Member> members = new ArrayList<>();
        Member member1 = Member.builder().id(1L).loginId("ssafy1").loginPw("ssafy1111!").name("임우택").email("ssafy1@ssafy.com").tel("010-1111-1111").birth("2000-01-01").gender(MALE).nickname("코코").roles(Collections.singletonList("MEMBER")).build();
        Member member2 = Member.builder().id(2L).loginId("ssafy2").loginPw("ssafy2222!").name("정유빈").email("ssafy2@ssafy.com").tel("010-2222-2222").birth("2000-02-02").gender(FEMALE).nickname("광주").roles(Collections.singletonList("MEMBER")).build();
        members.add(member1);
        members.add(member2);
        memberRepository.saveAll(members);
    }

    private void initNotice() {
        Admin admin = adminRepository.findByLoginId("admin").get();
        List<Notice> notices = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            notices.add(Notice.builder().pin("0").title("공지사항" + i).content("공지사항" + i + " 내용").active(ACTIVE).admin(admin).build());
        }
        noticeRepository.saveAll(notices);
    }

    private void initAdmin() {
        Admin admin = Admin.builder().id(1L).loginId("admin").loginPw("1!").name("임우택").email("admin@ssafy.com").tel("010-0000-0000").active(ACTIVE).build();
        adminRepository.save(admin);
    }
}
