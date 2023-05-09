package com.ssafy.tourmates.hotplace.repository;

import com.ssafy.tourmates.controller.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.hotplace.repository.dto.HotPlaceSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotPlaceRepositoryCustom {

    Page<HotPlaceResponse> searchByCondition(HotPlaceSearchCondition condition, Pageable pageable);
}
