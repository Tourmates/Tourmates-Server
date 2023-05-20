package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.member.request.*;
import com.ssafy.tourmates.client.api.dto.member.response.MemberDetailResponse;
import com.ssafy.tourmates.client.member.service.MemberService;
import com.ssafy.tourmates.client.member.service.dto.EditLoginPwDto;
import com.ssafy.tourmates.client.member.service.dto.MemberDetailDto;
import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.common.exception.EditException;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"회원"})
public class MemberApiController {

    private final MemberService memberService;

    @ApiOperation(value = "비밀번호변경")
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

    @ApiOperation(value = "이메일변경")
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

    @ApiOperation(value = "연락처변경")
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

    @ApiOperation(value = "닉네임변경")
    @PostMapping("/nickname")
    public int editNickname(@Valid @RequestBody EditNicknameRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        try {
            Long memberId = memberService.editNickname(loginId, request.getNewNickname());
            log.debug("memberId={}, newNickname={}", memberId, request.getNewNickname());
        } catch (DuplicateException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return -2;
        }

        return 1;
    }

    @ApiOperation(value = "회원 정보 조회")
    @PostMapping("/detail")
    public MemberDetailResponse getMemberDetail() {
        String loginId = SecurityUtil.getCurrentLoginId();

        MemberDetailDto memberDetailDto = memberService.getMemberDetail(loginId);

        MemberDetailResponse response = MemberDetailResponse.builder()
                .nickname(memberDetailDto.getNickname())
                .birth(memberDetailDto.getBirth())
                .emailId(memberDetailDto.getEmailId())
                .emailDomain(memberDetailDto.getEmailDomain())
                .startPhoneNumber(memberDetailDto.getStartPhoneNumber())
                .middlePhoneNumber(memberDetailDto.getMiddlePhoneNumber())
                .endPhoneNumber(memberDetailDto.getEndPhoneNumber())
                .build();

        return response;
    }

    @ApiOperation(value = "회원탈퇴")
    @PostMapping("/withdrawal")
    public int withdrawal(@Valid @RequestBody WithdrawalRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        try {
            Long memberId = memberService.withdrawal(loginId, request.getLoginPw());
            log.debug("memberId={}, loginPw={}", memberId, request.getLoginPw());
        } catch (EditException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return -2;
        }

        return 1;
    }
}
