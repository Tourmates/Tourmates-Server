package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentQueryRepository;
import com.ssafy.tourmates.client.hotplace.service.HotplaceCommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotplaceCommentQueryServiceImpl implements HotplaceCommentQueryService {

    private final HotPlaceCommentQueryRepository hotplaceCommentQueryRepository;


}
