package com.ssafy.tourmates.client.tripPlan.validator;

import com.ssafy.tourmates.client.tripPlan.TripPlan;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanRepository;

import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TripPlanValidator {

    private final TripPlanRepository tripPlanRepository;

    public TripPlan findById(Long id) {
        return tripPlanRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
