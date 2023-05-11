package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.client.hotplace.service.dto.EditHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceService {

    Long registerHotPlace(String loginId, AddHotPlaceDto dto);

    Long editHotPlace(Long hotPlaceId, EditHotPlaceDto dto);

    Long removeHotPlace(Long hotPlaceId);
}
