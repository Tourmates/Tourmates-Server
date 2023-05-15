package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.controller.dto.tripPlan.AddTripPlanRequest;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trips")
@Api(tags = {"여행"})
public class TripPlanController {

  private final TripPlanService tripPlanService;

  @ApiOperation(value = "여행계획 등록")
  @PostMapping("/register")
  public Long registerTripPlan(@Valid @RequestBody AddTripPlanRequest request){
    String loginId = SecurityUtil.getCurrentLoginId();
    AddTripPlanDto dto = AddTripPlanDto.builder()
        .title(request.getTitle())
        .build();

    Long savedTripPlanId = tripPlanService.registerTripPlan(loginId, dto);

    return savedTripPlanId;
  }

}
