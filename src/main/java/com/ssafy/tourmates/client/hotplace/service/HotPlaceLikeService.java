package com.ssafy.tourmates.client.hotplace.service;

public interface HotPlaceLikeService {

    Long registerHotPlaceLike(String loginId, Long hotPlaceId);

    void removeHotPlaceLike(Long likeId);
}
