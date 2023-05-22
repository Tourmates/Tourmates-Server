package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.client.api.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.EditHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.client.hotplace.repository.dto.HotPlaceSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface HotPlaceQueryService {

    List<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable);

    Long getTotalCount(HotPlaceSearchCondition condition);

    DetailHotPlaceResponse searchById(String loginId, Long hotPlaceId);

    EditHotPlaceResponse searchEditById(Long hotPlaceId);
}
