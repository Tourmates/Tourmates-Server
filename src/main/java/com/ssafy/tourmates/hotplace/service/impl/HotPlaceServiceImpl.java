package com.ssafy.tourmates.hotplace.service.impl;

import com.ssafy.tourmates.attraction.AttractionInfo;
import com.ssafy.tourmates.attraction.validator.AttractionValidator;
import com.ssafy.tourmates.common.domain.UploadFile;
import com.ssafy.tourmates.hotplace.HotPlace;
import com.ssafy.tourmates.hotplace.HotPlaceImage;
import com.ssafy.tourmates.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.hotplace.service.HotPlaceService;
import com.ssafy.tourmates.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.hotplace.service.dto.EditHotPlaceDto;
import com.ssafy.tourmates.hotplace.validator.HotPlaceValidator;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceRepository hotPlaceRepository;
    private final HotPlaceValidator hotPlaceValidator;
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

    @Override
    public Long editHotPlace(Long hotPlaceId, EditHotPlaceDto dto) {
        HotPlace findHotPlace = hotPlaceValidator.findById(hotPlaceId);
        List<HotPlaceImage> files = dto.getUploadFiles().stream()
                .map(uploadFile -> HotPlaceImage.builder()
                .uploadFile(uploadFile)
                .build()).collect(Collectors.toList());
        findHotPlace.changeHotPlace(dto.getTag(), dto.getTitle(), dto.getContent(), dto.getVisitedDate(), files);
        return findHotPlace.getId();
    }
}
