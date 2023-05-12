package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceCommentService;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.tourmates.client.hotplace.service.dto.EditHotPlaceCommentDto;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceCommentValidator;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceCommentServiceImpl implements HotPlaceCommentService {

    private final MemberValidator memberValidator;
    private final HotPlaceValidator hotplaceValidator;
    private final HotPlaceCommentValidator hotPlaceCommentValidator;
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

    @Override
    public Long editHotPlaceComment(Long hotPlaceId, Long hotPlaceCommentId, EditHotPlaceCommentDto dto) {

        HotPlaceComment findHotPlaceComment = hotPlaceCommentValidator.findById(hotPlaceCommentId);
        findHotPlaceComment.changeHotPlaceComment(dto.getContent());
        return findHotPlaceComment.getId();
    }

    @Override
    public void removeHotPlaceComment(Long hotPlaceCommentId) {

        hotplaceCommentRepository.deleteById(hotPlaceCommentId);

    }
}
