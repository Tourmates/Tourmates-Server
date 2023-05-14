package com.ssafy.tourmates.client.tripPlan.service.impl;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.client.tripPlan.TripPlan;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanRepository;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService {

  private final TripPlanRepository tripPlanRepository;
  private final TripPlanValidator tripPlanValidator;
  private final MemberValidator memberValidator;

  @Override
  public Long registerTripPlan(String loginId, AddTripPlanDto dto) {
    Member findMember = memberValidator.findByLoginId(loginId);

    TripPlan tripPlan = TripPlan.builder()
        .title(dto.getTitle())
        .hit(0)
        .active(ACTIVE)
        .member(findMember)
        .build();

    TripPlan savedTripPlan = tripPlanRepository.save(tripPlan);
    return savedTripPlan.getId();
  }
}
