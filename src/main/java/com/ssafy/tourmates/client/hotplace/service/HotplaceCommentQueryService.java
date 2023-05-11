package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.client.hotplace.HotplaceComment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface HotplaceCommentQueryService {

    List<HotplaceComment> searchById(Long hotplaceId);

}
