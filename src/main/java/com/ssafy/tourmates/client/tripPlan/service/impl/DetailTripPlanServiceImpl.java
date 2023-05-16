package com.ssafy.tourmates.client.tripPlan.service.impl;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.attraction.validator.AttractionValidator;
import com.ssafy.tourmates.client.tripPlan.DetailTripPlan;
import com.ssafy.tourmates.client.tripPlan.TripPlan;
import com.ssafy.tourmates.client.tripPlan.repository.DetailTripPlanRepository;
import com.ssafy.tourmates.client.tripPlan.service.DetailTripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddDetailTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.validator.DetailTripPlanValidator;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailTripPlanServiceImpl implements DetailTripPlanService {

    private final TripPlanValidator tripPlanValidator;
    private final DetailTripPlanValidator detailTripPlanValidator;
    private final AttractionValidator attractionValidator;
    private final DetailTripPlanRepository detailTripPlanRepository;

    @Override
    public List<Integer> registerDetailTripPlan(Long tripPlanId, AddDetailTripPlanDto dto) {

        TripPlan findTripPlan = tripPlanValidator.findById(tripPlanId);

        List<DetailTripPlan> detailTripPlanList = new ArrayList<>();
        List<Integer> contentIds = dto.getContentIds();

        for (int i = 0; i < contentIds.size(); i++) {
            AttractionInfo attractionInfo = attractionValidator.findById(contentIds.get(i));

            DetailTripPlan detailTripPlan = DetailTripPlan.builder()
                    .attractionInfo(attractionInfo)
                    .tripPlan(findTripPlan)
                    .build();

            detailTripPlanList.add(detailTripPlan);
        }

        findTripPlan.changeTripPlan(findTripPlan.getTitle(), detailTripPlanList);

        return contentIds;
    }

    @Override
    public void removeDetailTripPlan(Long detailTripPlanId) {
        DetailTripPlan detailTripPlan = detailTripPlanValidator.findById(detailTripPlanId);
        detailTripPlanRepository.deleteById(detailTripPlan.getId());
    }
}
