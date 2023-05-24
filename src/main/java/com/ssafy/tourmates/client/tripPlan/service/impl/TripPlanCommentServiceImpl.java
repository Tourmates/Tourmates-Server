package com.ssafy.tourmates.client.tripPlan.service.impl;

import com.ssafy.tourmates.client.api.dto.tripplan.response.TripPlanCommentResponse;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import com.ssafy.tourmates.client.tripPlan.TripPlan;
import com.ssafy.tourmates.client.tripPlan.TripPlanComment;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanCommentRepository;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanCommentService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.EditTripPlanCommentDto;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanCommentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TripPlanCommentServiceImpl implements TripPlanCommentService {

    private final MemberValidator memberValidator;
    private final TripPlanCommentValidator tripPlanCommentValidator;
    private final TripPlanCommentRepository tripPlanCommentRepository;

    @Override
    public Long registerTripPlanComment(String loginId, Long tripPlanId, AddTripPlanCommentDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);

        TripPlanComment tripPlanComment = TripPlanComment.builder()
                .content(dto.getContent())
                .member(findMember)
                .tripPlan(TripPlan.builder().id(tripPlanId).build())
                .build();

        TripPlanComment savedTripPlanComment = tripPlanCommentRepository.save(tripPlanComment);
        return savedTripPlanComment.getId();
    }

    @Override
    public Long editTripPlanComment(Long tripPlanId, Long tripPlanCommentId, EditTripPlanCommentDto dto) {
        TripPlanComment findTripPlanComment = tripPlanCommentValidator.findById(tripPlanCommentId);
        findTripPlanComment.changeComment(dto.getContent());
        return findTripPlanComment.getId();
    }

    @Override
    public Long removeTripPlanComment(Long tripPlanCommentId) {
        tripPlanCommentRepository.deleteById(tripPlanCommentId);
        return tripPlanCommentId;
    }

    @Override
    public List<TripPlanCommentResponse> searchAll(Long tripPlanId) {

        List<TripPlanComment> tripPlanCommentList = tripPlanCommentValidator.findByTripPlanId(tripPlanId);

        List<TripPlanCommentResponse> tripPlanCommentResponseList = new ArrayList<>();

        for(TripPlanComment comment: tripPlanCommentList){
            String nickname = comment.getMember().getNickname();
            LocalDateTime createdTime = comment.getCreatedDate();
            tripPlanCommentResponseList.add(new TripPlanCommentResponse(nickname,comment.getContent(), createdTime ));
        }
        return tripPlanCommentResponseList;
    }
}
