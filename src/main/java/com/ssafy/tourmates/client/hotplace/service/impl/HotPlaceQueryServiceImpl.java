package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.api.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.EditHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.client.hashtag.Hashtag;
import com.ssafy.tourmates.client.hashtag.HotPlaceHashtag;
import com.ssafy.tourmates.client.hashtag.repository.HashtagQueryRepository;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceQueryRepository;
import com.ssafy.tourmates.client.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotPlaceQueryServiceImpl implements HotPlaceQueryService {

    private final HotPlaceQueryRepository hotPlaceQueryRepository;

    @Override
    public List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable) {
        List<HotPlace> hotPlaces;
        if (condition.getType() == 2) {
            hotPlaces = hotPlaceQueryRepository.searchByHashtag(condition, pageable);
        } else {
            hotPlaces = hotPlaceQueryRepository.searchByCondition(condition, pageable);
        }
        return toHotPlaceResponse(hotPlaces);
    }

    @Override
    public List<HotPlaceResponse> searchMyHotPlace(String loginId, Pageable pageable) {
        List<HotPlace> hotPlaces = hotPlaceQueryRepository.searchByLoginId(loginId, pageable);
        return toHotPlaceResponse(hotPlaces);
    }

    private List<HotPlaceResponse> toHotPlaceResponse(List<HotPlace> hotPlaces){
        List<HotPlaceResponse> responses = new ArrayList<>();

        for (HotPlace hotPlace : hotPlaces) {
            List<String> tagNames = new ArrayList<>();
            for (HotPlaceHashtag hotPlaceHashtag : hotPlace.getHashtags()) {
                tagNames.add(hotPlaceHashtag.getHashtag().getTagName());
            }
            HotPlaceResponse hotPlaceResponse = HotPlaceResponse.builder()
                    .hotPlaceId(hotPlace.getId())
                    .title(hotPlace.getTitle())
                    .content(hotPlace.getContent())
                    .hit(hotPlace.getHit())
                    .visitedDate(hotPlace.getVisitedDate())
                    .storeFileName(hotPlace.getImages().get(0).getUploadFile().getStoreFileName())
                    .tags(tagNames)
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

        List<String> tags = hotPlace.getHashtags().stream()
                .map(hashtag -> hashtag.getHashtag().getTagName())
                .collect(Collectors.toList());

        return DetailHotPlaceResponse.builder()
                .hotPlaceId(hotPlace.getId())
                .title(hotPlace.getTitle())
                .content(hotPlace.getContent())
                .hit(hotPlace.getHit())
                .visitedDate(hotPlace.getVisitedDate())
                .isMine(hotPlace.getMember().getLoginId().equals(loginId))
                .nickname(hotPlace.getMember().getNickname())
                .images(images)
                .attractionTitle(hotPlace.getAttractionInfo().getTitle())
                .latitude(hotPlace.getAttractionInfo().getLatitude())
                .longitude(hotPlace.getAttractionInfo().getLongitude())
                .tags(tags)
                .build();
    }

    @Override
    public EditHotPlaceResponse searchEditById(Long hotPlaceId) {
        return hotPlaceQueryRepository.searchEditById(hotPlaceId);
    }


}
