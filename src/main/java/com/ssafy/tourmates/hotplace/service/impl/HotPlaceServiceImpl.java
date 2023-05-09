package com.ssafy.tourmates.hotplace.service.impl;

import com.ssafy.tourmates.attraction.AttractionInfo;
import com.ssafy.tourmates.attraction.validator.AttractionValidator;
import com.ssafy.tourmates.hotplace.HotPlace;
import com.ssafy.tourmates.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.hotplace.service.HotPlaceService;
import com.ssafy.tourmates.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceRepository hotPlaceRepository;
    private final MemberValidator memberValidator;
    private final AttractionValidator attractionValidator;

    @Override
    public Long registerHotPlace(String loginId, AddHotPlaceDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);
        AttractionInfo findAttraction = attractionValidator.findById(dto.getContentId());

        HotPlace hotPlace = HotPlace.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .tag(dto.getTag())
                .member(findMember)
                .attractionInfo(findAttraction)
                .build();

        HotPlace savedHotPlace = hotPlaceRepository.save(hotPlace);
        return savedHotPlace.getId();
    }
}
