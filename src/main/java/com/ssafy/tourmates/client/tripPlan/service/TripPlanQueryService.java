package com.ssafy.tourmates.client.tripPlan.service;

import com.ssafy.tourmates.client.api.dto.tripplan.response.DetailPlanResponse;
import com.ssafy.tourmates.client.api.dto.tripplan.response.PlanResponse;
import com.ssafy.tourmates.client.tripPlan.repository.dto.PlanSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface TripPlanQueryService {

    List<PlanResponse> searchByCondition(PlanSearchCondition condition, Pageable pageable);

    Long getTotalCount();

    DetailPlanResponse searchById(Long tripPlanId);

    List<PlanResponse> searchMyTripPlan(String loginId, Pageable pageable);
}
