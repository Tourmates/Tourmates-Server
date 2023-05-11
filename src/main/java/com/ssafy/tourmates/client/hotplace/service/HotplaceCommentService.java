package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HotplaceCommentService {

    Long registerHotplaceComment(String loginId, Long hotPlaceId, AddHotPlaceCommentDto dto);
}
