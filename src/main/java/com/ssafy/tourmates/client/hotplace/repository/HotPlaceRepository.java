package com.ssafy.tourmates.client.hotplace.repository;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotPlaceRepository extends JpaRepository<HotPlace, Long> {
}
