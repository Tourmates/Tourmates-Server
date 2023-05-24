package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceCommentResponse;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.tourmates.client.hotplace.service.dto.EditHotPlaceCommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HotPlaceCommentService {

    Long registerHotPlaceComment(String loginId, Long hotPlaceId, AddHotPlaceCommentDto dto);

    Long editHotPlaceComment(Long hotPlaceCommentId, EditHotPlaceCommentDto dto);

    Long removeHotPlaceComment(Long hotPlaceCommentId);

    List<HotPlaceCommentResponse> searchAll(Long hotPlaceId);
}
