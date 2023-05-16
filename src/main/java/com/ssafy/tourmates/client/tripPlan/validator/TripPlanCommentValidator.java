package com.ssafy.tourmates.client.tripPlan.validator;

import com.ssafy.tourmates.client.tripPlan.TripPlanComment;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class TripPlanCommentValidator {

    private final TripPlanCommentRepository tripPlanCommentRepository;

    public TripPlanComment findById(Long id) {
        return tripPlanCommentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
