package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.api.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.EditHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.tourmates.client.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {

    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        List<HotPlace> hotPlaces = hotPlaceQueryRepository.searchByCondition(condition, pageable);
        List<HotPlaceResponse> responses = toHotPlaceResponse(hotPlaces);
        return responses;
    }

    @Override
    public List<HotPlaceResponse> searchMyHotPlace(String loginId, Pageable pageable) {
        List<HotPlace> hotPlaces = hotPlaceQueryRepository.searchByLoginId(loginId, pageable);
        List<HotPlaceResponse> responses = toHotPlaceResponse(hotPlaces);
        return responses;
    }

    private List<HotPlaceResponse> toHotPlaceResponse(List<HotPlace> hotPlaces){
        List<HotPlaceResponse> responses = new ArrayList<>();

        for (HotPlace hotPlace : hotPlaces) {
            HotPlaceResponse hotPlaceResponse = HotPlaceResponse.builder()
                    .hotPlaceId(hotPlace.getId())
                    .tag(hotPlace.getTag())
                    .title(hotPlace.getTitle())
                    .content(hotPlace.getContent())
                    .hit(hotPlace.getHit())
                    .visitedDate(hotPlace.getVisitedDate())
                    .storeFileName(hotPlace.getImages().get(0).getUploadFile().getStoreFileName())
                    .build();
            responses.add(hotPlaceResponse);
        }
        return responses;
    }

    @Override
    public Long getTotalCount(HotPlaceSearchCondition condition) {
        return hotPlaceQueryRepository.totalCount(condition);
    }

    @Override
    public DetailHotPlaceResponse searchById(String loginId, Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceQueryRepository.searchById(hotPlaceId);
        List<String> images = hotPlace.getImages().stream()
                .map(image -> image.getUploadFile().getStoreFileName())
                .collect(Collectors.toList());
        List<String> comments = hotPlace.getComments().stream()
                .map(HotPlaceComment::getContent)
                .collect(Collectors.toList());

        return DetailHotPlaceResponse.builder()
                .hotPlaceId(hotPlace.getId())
                .tag(hotPlace.getTag())
                .title(hotPlace.getTitle())
                .content(hotPlace.getContent())
                .hit(hotPlace.getHit())
                .visitedDate(hotPlace.getVisitedDate())
                .isMine(hotPlace.getMember().getLoginId().equals(loginId))
                .nickname(hotPlace.getMember().getNickname())
                .images(images)
                .comments(comments)
                .attractionTitle(hotPlace.getAttractionInfo().getTitle())
                .latitude(hotPlace.getAttractionInfo().getLatitude())
                .longitude(hotPlace.getAttractionInfo().getLongitude())
                .build();
    }

    @Override
    public EditHotPlaceResponse searchEditById(Long hotPlaceId) {
        return hotPlaceQueryRepository.searchEditById(hotPlaceId);
    }


}
