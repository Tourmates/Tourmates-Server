package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceLike;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceLikeRepository;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceLikeService;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceCommentValidator;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceLikeServiceImpl implements HotPlaceLikeService {

    private final HotPlaceLikeRepository hotPlaceLikeRepository;
    private final MemberValidator memberValidator;
    private final HotPlaceValidator hotplaceValidator;
    private final HotPlaceCommentValidator hotPlaceCommentValidator;

    @Override
    public Long registerHotPlaceLike(String loginId, Long hotPlaceId) {

        Member findMember = memberValidator.findByLoginId(loginId);
        HotPlace findHotPlace = hotplaceValidator.findById(hotPlaceId);

        HotPlaceLike hotPlaceLike = HotPlaceLike.builder()
                .member(findMember)
                .hotPlace(findHotPlace)
                .build();

        HotPlaceLike savedHotPlaceLike = hotPlaceLikeRepository.save(hotPlaceLike);
        return savedHotPlaceLike.getId();
    }

    @Override
    public void removeHotPlaceLike(Long likeId) {
        hotPlaceLikeRepository.deleteById(likeId);
    }
}
