package com.ssafy.tourmates.client.hotplace.service.impl;

import com.ssafy.tourmates.client.hotplace.HotplaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotplaceCommentQueryRepository;
import com.ssafy.tourmates.client.hotplace.repository.HotplaceCommentRepository;
import com.ssafy.tourmates.client.hotplace.service.HotplaceCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotplaceCommentServiceImpl implements HotplaceCommentService {

    private final HotplaceCommentRepository hotplaceCommentRepository;

}
