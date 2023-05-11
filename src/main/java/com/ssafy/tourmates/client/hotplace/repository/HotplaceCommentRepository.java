package com.ssafy.tourmates.client.hotplace.repository;

import com.ssafy.tourmates.client.hotplace.HotplaceComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HotplaceCommentRepository extends JpaRepository<HotplaceComment, Long> {

}
