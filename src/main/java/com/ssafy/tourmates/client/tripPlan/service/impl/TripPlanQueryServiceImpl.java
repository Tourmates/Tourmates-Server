package com.ssafy.tourmates.client.tripPlan.service.impl;

import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanResponse;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanQueryRepository;
import com.ssafy.tourmates.client.tripPlan.repository.dto.PlanSearchCondition;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlanQueryServiceImpl implements TripPlanQueryService {

    private final TripPlanQueryRepository tripPlanQueryRepository;

    @Override
    public List<PlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable) {
        return tripPlanQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public Long getTotalCount() {
        return tripPlanQueryRepository.totalCount();
    }
}
