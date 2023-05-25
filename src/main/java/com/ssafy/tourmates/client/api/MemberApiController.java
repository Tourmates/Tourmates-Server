package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.board.response.BoardResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.member.request.*;
import com.ssafy.tourmates.client.api.dto.member.response.MemberDetailResponse;
import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanResponse;
import com.ssafy.tourmates.client.board.service.BoardQueryService;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceQueryService;
import com.ssafy.tourmates.client.member.service.MemberService;
import com.ssafy.tourmates.client.member.service.dto.EditLoginPwDto;
import com.ssafy.tourmates.client.member.service.dto.EditMyPersonalDto;
import com.ssafy.tourmates.client.member.service.dto.MemberDetailDto;
import com.ssafy.tourmates.client.tripPlan.repository.dto.PlanSearchCondition;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanQueryService;
import com.ssafy.tourmates.common.exception.DuplicateException;
import com.ssafy.tourmates.common.exception.EditException;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/my")
@Api(tags = {"회원"})
public class MemberApiController {

    private final BoardQueryService boardQueryService;
    private final MemberService memberService;
    private final HotPlaceQueryService hotPlaceQueryService;
    private final TripPlanQueryService tripPlanQueryService;

//    @ApiOperation(value = "나의 여행 계획 조회")
//    public Result<List<PlanResponse>> myTripPlans(@RequestParam(defaultValue = "1") Integer pageNumber){
//
//        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 10);
//        List<PlanResponse> responses = tripPlanQueryService.searchMyTripPlan(loginId, pageRequest);
//        log.debug("response size: {}", responses.size());
//
//        return new Result<>(responses);
//    }



    @ApiOperation(value = "여행계획 조회")
    @GetMapping("/tripPlans")
    public Result<List<PlanResponse>> myTripPlans(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") Integer type,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        String loginId = SecurityUtil.getCurrentLoginId();

        //0: 타이틀, 1: 닉네임, 2: 관광지이름
//        PlanSearchConditionBuilder builder = builder();
//        switch (type) {
//            case 1:
//                builder.nickname(keyword);
//                break;
//            case 2:
//                builder.attractionTitle(keyword);
//                break;
//            default:
//                builder.title(keyword);
//                break;
//        }
        PlanSearchCondition condition = PlanSearchCondition.builder().build();
        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 10);

        List<PlanResponse> responses = tripPlanQueryService.searchByCondition(loginId, condition, pageRequest);
        log.debug("size={}", responses.size());
        return new Result<>(responses);
    }

    @ApiOperation(value = "나의 여행계획 총 갯수 조회")
    @GetMapping("/tripPlans/totalCount")
    public Long totalCount() {
        String loginId = SecurityUtil.getCurrentLoginId();
        return tripPlanQueryService.getTotalCount(loginId);
    }


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

    @ApiOperation("개인 정보 전체 변경")
    @PostMapping("/personal/info")
    public int editMyPersonal(@Valid @RequestBody EditMyPersonalRequest request){
        String loginId = SecurityUtil.getCurrentLoginId();

        EditMyPersonalDto dto = EditMyPersonalDto.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .emailId(request.getEmailId())
                .emailDomain(request.getEmailDomain())
                .startPhoneNumber(request.getStartPhoneNumber())
                .middlePhoneNumber(request.getMiddlePhoneNumber())
                .endPhoneNumber(request.getEndPhoneNumber())
                .build();

        try {
            Long memberId = memberService.editMyPersonal(loginId, dto);
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
    @GetMapping("/detail")
    public MemberDetailResponse getMemberDetail() {

        String loginId = SecurityUtil.getCurrentLoginId();

        MemberDetailDto memberDetailDto = memberService.getMemberDetail(loginId);

        MemberDetailResponse response = MemberDetailResponse.builder()
                .username(memberDetailDto.getUsername())
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

    @ApiOperation("나의 게시물 조회")
    @GetMapping("/boards")
    public BoardApiController.ResultPage<List<BoardResponse>> searchBoards(
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        String loginId = SecurityUtil.getCurrentLoginId();
        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 20);
        List<BoardResponse> boardResponses = boardQueryService.searchByLoginId(pageRequest, loginId);
        log.debug("responses size={}", boardResponses.size());
        log.debug("pageNumber={}", pageNumber);

        return new BoardApiController.ResultPage<>(boardResponses);
    }

    @ApiOperation(value = "나의 핫플레이스 조회")
    @GetMapping("/hotPlaces")
    public Result<List<HotPlaceResponse>> myHotplaces(@RequestParam(defaultValue = "1") Integer pageNumber){
        String loginId = SecurityUtil.getCurrentLoginId();

        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 10);
        List<HotPlaceResponse> responses = hotPlaceQueryService.searchMyHotPlace(loginId, pageRequest);

        return new Result<>(responses);
    }


    @ApiOperation(value = "회원탈퇴")
    @PostMapping("/withdrawal")
    public int withdrawal(@Valid @RequestBody WithdrawalRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        log.debug("loginId= {}", loginId);

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


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
