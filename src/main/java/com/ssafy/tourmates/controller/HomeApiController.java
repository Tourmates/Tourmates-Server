package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.controller.dto.member.request.ForgotLoginIdRequest;
import com.ssafy.tourmates.controller.dto.member.request.ForgotLoginPwRequest;
import com.ssafy.tourmates.controller.dto.member.request.JoinMemberRequest;
import com.ssafy.tourmates.member.service.MemberQueryService;
import com.ssafy.tourmates.member.service.MemberService;
import com.ssafy.tourmates.member.service.dto.JoinMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeApiController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/join")
    public int joinMember(@Valid @RequestBody JoinMemberRequest request) {
        JoinMemberDto dto = JoinMemberDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .name(request.getName())
                .email(request.getEmail())
                .tel(request.getTel())
                .birth(request.getBirth())
                .gender(request.getGender())
                .nickname(request.getNickname())
                .build();

        try {
            Long memberId = memberService.joinMember(dto);
            log.debug("memberId = {}", memberId);
        } catch (DuplicateException e) {
            return -1;
        }
        return 1;
    }

    @PostMapping("/forgot/loginId")
    public String forgotLoginId(@Valid @RequestBody ForgotLoginIdRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().length() == 0) {
            return memberQueryService.forgotLoginIdByTel(request.getTel());
        } else {
            return memberQueryService.forgotLoginIdByEmail(request.getEmail());
        }
    }

    @PostMapping("/forgot/loginPw")
    public String forgotLoginPw(@Valid @RequestBody ForgotLoginPwRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().length() == 0) {
            return memberQueryService.forgotLoginPwByTel(request.getLoginId(), request.getTel());
        } else {
            return memberQueryService.forgotLoginPwByEmail(request.getLoginId(), request.getEmail());
        }
    }
}
