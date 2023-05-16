package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.controller.dto.tripPlan.AddDetailPlanRequest;
import com.ssafy.tourmates.client.controller.dto.tripPlan.AddTripPlanCommentRequest;
import com.ssafy.tourmates.client.controller.dto.tripPlan.AddTripPlanRequest;
import com.ssafy.tourmates.client.controller.dto.tripPlan.EditTripPlanRequest;
import com.ssafy.tourmates.client.tripPlan.service.DetailTripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanCommentService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddDetailTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trips")
@Api(tags = {"여행"})
public class TripPlanController {

    private final TripPlanService tripPlanService;
    private final DetailTripPlanService detailTripPlanService;
    private final TripPlanCommentService tripPlanCommentService;

    @ApiOperation(value = "여행계획 등록")
    @PostMapping("/register")
    public Long registerTripPlan(@Valid @RequestBody AddTripPlanRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        AddTripPlanDto dto = AddTripPlanDto.builder()
                .title(request.getTitle())
                .build();

        Long savedTripPlanId = tripPlanService.registerTripPlan(loginId, dto);

        return savedTripPlanId;
    }

    @ApiOperation(value = "여행 계획 댓글 등록")
    @PostMapping("/{tripPlanId}/comments/register")
    public Long registerTripPlanComment(@PathVariable Long tripPlanId, @Valid @RequestBody AddTripPlanCommentRequest request) {

        String loginId = SecurityUtil.getCurrentLoginId();

        AddTripPlanCommentDto dto = AddTripPlanCommentDto.builder()
                .content(request.getComment())
                .build();

        Long tripPlanCommentId = tripPlanCommentService.registerTripPlanComment(loginId, tripPlanId, dto);

        return tripPlanCommentId;
    }

    @ApiOperation(value = "세부 여행 계획 등록")
    @PostMapping("/{tripPlanId}/detailPlan/register")
    public List<Integer> registerDetailPlan(@PathVariable Long tripPlanId, @Valid @RequestBody AddDetailPlanRequest request) {

        AddDetailTripPlanDto dto = AddDetailTripPlanDto.builder()
                .contentIds(request.getContentIds())
                .build();

        List<Integer> contentIds = detailTripPlanService.registerDetailTripPlan(tripPlanId, dto);
        return contentIds;
    }

    @ApiOperation(value = "여행계획 수정")
    @PostMapping("/{tripPlanId}/edit")
    public Long editTripPlan(@PathVariable Long tripPlanId, EditTripPlanRequest request) {
        EditTripPlanDto dto = EditTripPlanDto.builder()
                .title(request.getTitle())
                .build();

        Long editTripPlanId = tripPlanService.editTripPlan(tripPlanId, dto);
        return editTripPlanId;
    }


    @ApiOperation(value = "여행계획 삭제")
    @PostMapping("/{tripPlanId}/remove")
    public Long removeTripPlan(@PathVariable Long tripPlanId) {
        Long removedTripPlanId = tripPlanService.removeTripPlan(tripPlanId);
        return removedTripPlanId;
    }

    @ApiOperation(value = "세부 여행 계획 삭제")
    @PostMapping("/{tripPlanId}/detail/{detailTripPlanId}/remove")
    public Integer removeDetailTripPlan(@PathVariable Long tripPlanId, @PathVariable Long detailTripPlanId) {
        detailTripPlanService.removeDetailTripPlan(detailTripPlanId);
        return 1;
    }
}
