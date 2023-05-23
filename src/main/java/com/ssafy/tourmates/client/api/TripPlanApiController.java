package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.tripplan.request.AddDetailPlanRequest;
import com.ssafy.tourmates.client.api.dto.tripplan.request.AddTripPlanRequest;
import com.ssafy.tourmates.client.api.dto.tripplan.request.EditTripPlanRequest;
import com.ssafy.tourmates.client.api.dto.tripplan.response.DetailPlanResponse;
import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanResponse;
import com.ssafy.tourmates.client.tripPlan.repository.dto.PlanSearchCondition;
import com.ssafy.tourmates.client.tripPlan.service.DetailTripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanQueryService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.*;
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

import static com.ssafy.tourmates.client.tripPlan.repository.dto.PlanSearchCondition.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tripPlans")
@Api(tags = {"여행"})
public class TripPlanApiController {

    private final TripPlanService tripPlanService;
    private final TripPlanQueryService tripPlanQueryService;
    private final DetailTripPlanService detailTripPlanService;

    @ApiOperation(value = "여행계획 조회")
    @GetMapping
    public Result<?> searchTripPlans(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "0") Integer type,
        @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        //0: 타이틀, 1: 닉네임, 2: 관광지이름
        PlanSearchConditionBuilder builder = builder();
        switch (type) {
            case 1:
                builder.nickname(keyword);
                break;
            case 2:
                builder.attractionTitle(keyword);
                break;
            default:
                builder.title(keyword);
                break;
        }
        PlanSearchCondition condition = builder.build();
        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 10);

        List<PlanResponse> responses = tripPlanQueryService.searchByCondition(condition, pageRequest);
        log.debug("size={}", responses.size());
        return new Result<>(responses);
    }

    @ApiOperation(value = "여행계획 총 갯수 조회")
    @GetMapping("/totalCount")
    public Long totalCount() {
        return tripPlanQueryService.getTotalCount();
    }

    @ApiOperation(value = "여행계획 등록")
    @PostMapping("/register")
    public Long registerTripPlan(@Valid @RequestBody AddTripPlanRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        AddTripPlanDto dto = AddTripPlanDto.builder()
                .title(request.getTitle())
                .contentIds(request.getContentIds())
                .build();

        Long savedTripPlanId = tripPlanService.registerTripPlan(loginId, dto);
        log.debug("savedTripPlanId={}", savedTripPlanId);
        return savedTripPlanId;
    }

    @ApiOperation(value = "여행계획 상세 조회")
    @GetMapping("/{tripPlanId}")
    public Result<?> searchTripPlan(@PathVariable Long tripPlanId) {
        DetailPlanResponse response = tripPlanQueryService.searchById(tripPlanId);
        return new Result<>(response);
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

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
