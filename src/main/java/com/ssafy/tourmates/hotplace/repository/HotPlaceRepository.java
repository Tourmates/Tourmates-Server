package com.ssafy.tourmates.hotplace.repository;

import com.ssafy.tourmates.hotplace.HotPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotPlaceRepository extends JpaRepository<HotPlace, Long> {
}
