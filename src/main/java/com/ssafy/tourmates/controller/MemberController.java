package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.common.exception.EditException;
import com.ssafy.tourmates.controller.dto.member.request.EditLoginPwRequest;
import com.ssafy.tourmates.jwt.SecurityUtil;
import com.ssafy.tourmates.member.service.MemberService;
import com.ssafy.tourmates.member.service.dto.EditLoginPwDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/my")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/loginPw")
    public int editLoginPw(@Valid @RequestBody EditLoginPwRequest request) {
        if (!request.getNewLoginPw().equals(request.getCheckedNewLoginPw())) {
            return -1;
        }

        String loginId = SecurityUtil.getCurrentLoginId();
        EditLoginPwDto dto = EditLoginPwDto.builder()
                .currentLoginPw(request.getCurrentLoginPw())
                .newLoginPw(request.getNewLoginPw())
                .build();

        try {
            Long memberId = memberService.editLoginPw(loginId, dto);
            log.debug("memberId={}, currentLoginPw={}, newLoginPw={}", memberId, dto.getCurrentLoginPw(), dto.getNewLoginPw());
        } catch (EditException e) {
            return -1;
        }
        return 1;
    }
}
