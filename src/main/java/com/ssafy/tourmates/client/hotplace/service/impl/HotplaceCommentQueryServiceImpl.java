package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotplaceCommentQueryRepository;
import com.ssafy.tourmates.client.hotplace.repository.HotplaceCommentRepository;
import com.ssafy.tourmates.client.hotplace.service.HotplaceCommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotplaceCommentQueryServiceImpl implements HotplaceCommentQueryService {

    private final HotplaceCommentQueryRepository hotplaceCommentQueryRepository;


}
