package com.ssafy.tourmates.hotplace.service.impl;

import com.ssafy.tourmates.controller.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.controller.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.hotplace.HotPlace;
import com.ssafy.tourmates.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.tourmates.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.tourmates.hotplace.service.HotPlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {

    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        return hotPlaceQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public DetailHotPlaceResponse searchById(Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceQueryRepository.searchById(hotPlaceId);
        List<String> images = hotPlace.getImages().stream()
                .map(image -> image.getUploadFile().getStoreFileName())
                .collect(Collectors.toList());

        return DetailHotPlaceResponse.builder()
                .hotPlaceId(hotPlace.getId())
                .tag(hotPlace.getTag())
                .title(hotPlace.getTitle())
                .content(hotPlace.getContent())
                .hit(hotPlace.getHit())
                .visitedDate(hotPlace.getVisitedDate())
                .nickname(hotPlace.getMember().getNickname())
                .images(images)
                .build();
    }
}
