package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.tourmates.client.hotplace.service.HotplaceCommentService;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotplaceCommentServiceImpl implements HotplaceCommentService {

    private final MemberValidator memberValidator;
    private final HotPlaceValidator hotplaceValidator;
    private final HotPlaceCommentRepository hotplaceCommentRepository;

    @Override
    public Long registerHotplaceComment(String loginId, Long hotPlaceId, AddHotPlaceCommentDto dto) {

        Member findMember = memberValidator.findByLoginId(loginId);
        HotPlace findHotPlace = hotplaceValidator.findById(hotPlaceId);

        HotPlaceComment hotPlaceComment = HotPlaceComment.builder()
                .content(dto.getContent())
                .member(findMember)
                .hotPlace(findHotPlace)
                .build();

        HotPlaceComment savedHotPlaceComment = hotplaceCommentRepository.save(hotPlaceComment);
        return savedHotPlaceComment.getId();
    }
}
