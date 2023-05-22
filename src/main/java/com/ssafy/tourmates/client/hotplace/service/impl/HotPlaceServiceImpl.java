package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceImage;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceService;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.client.hotplace.service.dto.EditHotPlaceDto;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceRepository hotPlaceRepository;
    private final HotPlaceValidator hotPlaceValidator;
    private final MemberValidator memberValidator;

    @Override
    public Long registerHotPlace(String loginId, AddHotPlaceDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);

        HotPlace hotPlace = HotPlace.createHotPlace(dto.getTag(), dto.getTitle(), dto.getContent(), dto.getVisitedDate(), findMember, dto.getContentId(), dto.getUploadFiles());
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

    @Override
    public Long increaseHit(Long hotPlaceId) {
        HotPlace findHotPlace = hotPlaceValidator.findById(hotPlaceId);
        findHotPlace.increaseHit();
        return findHotPlace.getId();
    }

    @Override
    public Long removeHotPlace(Long hotPlaceId) {
        HotPlace findHotPlace = hotPlaceValidator.findById(hotPlaceId);
        findHotPlace.deActive();
        return findHotPlace.getId();
    }
}
