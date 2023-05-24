package com.ssafy.tourmates.client.hotplace.repository;

import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotPlaceCommentRepository extends JpaRepository<HotPlaceComment, Long> {
    List<HotPlaceComment> findAllByHotPlaceId(@Param("hotPlaceId") Long hotPlaceId);
}
