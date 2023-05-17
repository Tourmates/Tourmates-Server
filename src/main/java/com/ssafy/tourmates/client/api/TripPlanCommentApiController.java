package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.tripPlan.AddTripPlanCommentRequest;
import com.ssafy.tourmates.client.api.dto.tripPlan.EditTripPlanCommentReqeust;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanCommentService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanCommentDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trips/{tripPlanId}/comments")
@Api(tags = {"여행 댓글"})
public class TripPlanCommentApiController {

    private final TripPlanCommentService tripPlanCommentService;

    @ApiOperation(value = "여행 계획 댓글 등록")
    @PostMapping("/register")
    public Long registerTripPlanComment(
            @PathVariable Long tripPlanId,
            @Valid @RequestBody AddTripPlanCommentRequest request) {

        String loginId = SecurityUtil.getCurrentLoginId();

        AddTripPlanCommentDto dto = AddTripPlanCommentDto.builder()
                .content(request.getComment())
                .build();

        Long tripPlanCommentId = tripPlanCommentService.registerTripPlanComment(loginId, tripPlanId, dto);
        log.debug("tripPlanCommentId={}", tripPlanCommentId);
        return tripPlanCommentId;
    }

    @ApiOperation(value = "여행 계획 댓글 수정")
    @PostMapping("/{tripPlanCommentId}/edit")
    public Long editTripPlanComment(
            @PathVariable Long tripPlanId,
            @PathVariable Long tripPlanCommentId,
            @Valid @RequestBody EditTripPlanCommentReqeust request) {

        EditTripPlanCommentDto dto = EditTripPlanCommentDto.builder()
                .content(request.getContent())
                .build();

        Long editTripPlanCommentId = tripPlanCommentService.editTripPlanComment(tripPlanId, tripPlanCommentId, dto);
        log.debug("editTripPlanCommentId={}", editTripPlanCommentId);
        return editTripPlanCommentId;
    }

    @ApiOperation(value = "여행 계획 댓글 삭제")
    @PostMapping("/{tripPlanCommentId}/remove")
    public int removeTripPlanComment(
            @PathVariable Long tripPlanId,
            @PathVariable Long tripPlanCommentId) {
        log.debug("tripPlanId={}", tripPlanId);
        Long removedTripPlanId = tripPlanCommentService.removeTripPlanComment(tripPlanCommentId);
        log.debug("removedTripPlanId={}", removedTripPlanId);
        return 1;
    }
}
