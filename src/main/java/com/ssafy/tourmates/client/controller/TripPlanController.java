package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.controller.dto.tripPlan.AddDetailPlanRequest;
import com.ssafy.tourmates.client.controller.dto.tripPlan.AddTripPlanRequest;
import com.ssafy.tourmates.client.controller.dto.tripPlan.EditTripPlanRequest;
import com.ssafy.tourmates.client.tripPlan.service.DetailTripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddDetailTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trips")
@Api(tags = {"여행"})
public class TripPlanController {

    private final TripPlanService tripPlanService;
    private final DetailTripPlanService detailTripPlanService;

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

    @ApiOperation(value = "세부 여행 계획 등록")
    @PostMapping("/{tripPlanId}/detailPlan/register")
    public List<Integer> registerDetailPlan(@PathVariable Long tripPlanId, @Valid @RequestBody AddDetailPlanRequest request){

        AddDetailTripPlanDto dto = AddDetailTripPlanDto.builder()
                .contendIds(request.getContendIds())
                .build();

        List<Integer> contentIds = detailTripPlanService.registerDetailTripPlan(tripPlanId, dto);
        return contentIds;
    }

    @ApiOperation(value = "여행계획 수정")
    @PostMapping("/{tripPlanId}/edit")
    public Long editTripPlan(@PathVariable Long tripPlanId, EditTripPlanRequest request){
        EditTripPlanDto dto = EditTripPlanDto.builder()
            .title(request.getTitle())
            .build();

        Long editTripPlanId = tripPlanService.editTripPlan(tripPlanId, dto);
        return editTripPlanId;
  }

    @ApiOperation(value = "여행계획 삭제")
    @PostMapping("/{tripPlanId}/remove")
    public Long removeTripPlan(@PathVariable Long tripPlanId){
        Long removedTripPlanId = tripPlanService.removeTripPlan(tripPlanId);
        return removedTripPlanId;
    }
}
