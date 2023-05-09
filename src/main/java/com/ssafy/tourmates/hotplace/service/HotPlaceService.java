package com.ssafy.tourmates.hotplace.service;

import com.ssafy.tourmates.hotplace.service.dto.AddHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceService {

    Long registerHotPlace(String loginId, AddHotPlaceDto dto);

}
