package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.common.exception.EditException;
import com.ssafy.tourmates.controller.dto.member.request.EditEmailRequest;
import com.ssafy.tourmates.controller.dto.member.request.EditLoginPwRequest;
import com.ssafy.tourmates.controller.dto.member.request.EditTelRequest;
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
import java.util.NoSuchElementException;

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

    @PostMapping("/email")
    public int editEmail(@Valid @RequestBody EditEmailRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        try {
            Long memberId = memberService.editEmail(loginId, request.getNewEmail());
            log.debug("memberId={}, newEmail={}", memberId, request.getNewEmail());
        } catch (NoSuchElementException e) {
            return -2;
        }
        return 1;
    }

    @PostMapping("/tel")
    public int editTel(@Valid @RequestBody EditTelRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        try {
            Long memberId = memberService.editTel(loginId, request.getNewTel());
            log.debug("memberId={}, newTel={}", memberId, request.getNewTel());
        } catch (DuplicateException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return -2;
        }

        return 1;
    }
}
