package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.member.request.LoginRequest;
import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.client.api.dto.member.request.ForgotLoginIdRequest;
import com.ssafy.tourmates.client.api.dto.member.request.ForgotLoginPwRequest;
import com.ssafy.tourmates.client.api.dto.member.request.JoinMemberRequest;
import com.ssafy.tourmates.client.member.service.MemberQueryService;
import com.ssafy.tourmates.client.member.service.MemberService;
import com.ssafy.tourmates.client.member.service.dto.JoinMemberDto;
import com.ssafy.tourmates.jwt.TokenInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"계정"})
public class HomeApiController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;

    @ApiOperation(value = "회원가입")
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

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginRequest request) {
        log.debug("request={}", request);
        return memberQueryService.login(request.getLoginId(), request.getLoginPw());
    }

    @ApiOperation(value = "아이디찾기")
    @PostMapping("/forgot/loginId")
    public String forgotLoginId(@Valid @RequestBody ForgotLoginIdRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().length() == 0) {
            return memberQueryService.forgotLoginIdByTel(request.getTel());
        } else {
            return memberQueryService.forgotLoginIdByEmail(request.getEmail());
        }
    }

    @ApiOperation(value = "비밀번호찾기")
    @PostMapping("/forgot/loginPw")
    public String forgotLoginPw(@Valid @RequestBody ForgotLoginPwRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().length() == 0) {
            return memberQueryService.forgotLoginPwByTel(request.getLoginId(), request.getTel());
        } else {
            return memberQueryService.forgotLoginPwByEmail(request.getLoginId(), request.getEmail());
        }
    }
}
