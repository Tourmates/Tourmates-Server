package com.ssafy.tourmates.client.tripPlan.service.impl;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.client.tripPlan.TripPlan;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanRepository;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final TripPlanValidator tripPlanValidator;
    private final MemberValidator memberValidator;

    @Override
    public Long registerTripPlan(String loginId, AddTripPlanDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);
        TripPlan tripPlan = TripPlan.createTripPlan(dto.getTitle(), findMember.getId(), dto.getContentIds());
        TripPlan savedTripPlan = tripPlanRepository.save(tripPlan);
        return savedTripPlan.getId();
    }

    @Override
    public Long editTripPlan(Long tripPlanId, EditTripPlanDto dto) {
        TripPlan findTripPlan = tripPlanValidator.findById(tripPlanId);
        findTripPlan.changeTripPlan(dto.getTitle(), findTripPlan.getDetailTripPlans());
        return findTripPlan.getId();
    }

    @Override
    public Long removeTripPlan(Long tripPlanId) {
        TripPlan findTripPlan = tripPlanValidator.findById(tripPlanId);
        findTripPlan.deActive();
        return findTripPlan.getId();
    }
}
