package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentQueryRepository;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceCommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotPlaceCommentQueryServiceImpl implements HotPlaceCommentQueryService {

    private final HotPlaceCommentQueryRepository hotplaceCommentQueryRepository;


}
