package com.ssafy.tourmates.client.tripPlan.validator;

import com.ssafy.tourmates.client.tripPlan.DetailTripPlan;
import com.ssafy.tourmates.client.tripPlan.repository.DetailTripPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class DetailTripPlanValidator {

    private final DetailTripPlanRepository detailTripPlanRepository;

    public DetailTripPlan findById(long id) {
        return detailTripPlanRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
