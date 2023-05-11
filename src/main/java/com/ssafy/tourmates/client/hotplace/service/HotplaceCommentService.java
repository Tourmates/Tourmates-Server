package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.client.hotplace.HotplaceComment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HotplaceCommentService {

    List<HotplaceComment> searchById(Long hotplaceId);

}
