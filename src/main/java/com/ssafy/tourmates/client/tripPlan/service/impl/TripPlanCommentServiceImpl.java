package com.ssafy.tourmates.client.tripPlan.service.impl;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.client.tripPlan.TripPlan;
import com.ssafy.tourmates.client.tripPlan.TripPlanComment;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanCommentRepository;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanCommentService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanCommentValidator;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TripPlanCommentServiceImpl implements TripPlanCommentService {

    private final MemberValidator memberValidator;
    private final TripPlanValidator tripPlanValidator;
    private final TripPlanCommentValidator tripPlanCommentValidator;
    private final TripPlanCommentRepository tripPlanCommentRepository;

    @Override
    public Long registerTripPlanComment(String loginId, Long tripPlanId, AddTripPlanCommentDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);
        TripPlan findTripPlan = tripPlanValidator.findById(tripPlanId);

        TripPlanComment tripPlanComment = TripPlanComment.builder()
                .tripPlan(findTripPlan)
                .member(findMember)
                .content(dto.getContent())
                .build();

        TripPlanComment savedTripPlanComment = tripPlanCommentRepository.save(tripPlanComment);
        return savedTripPlanComment.getId();
    }

    @Override
    public Long editTripPlanComment(Long tripPlanId, Long tripPlanCommentId, EditTripPlanCommentDto dto) {

        TripPlanComment findTripPlanComment = tripPlanCommentValidator.findById(tripPlanCommentId);
        findTripPlanComment.changeTripPlanComment(dto.getContent());
        return findTripPlanComment.getId();
    }
}
