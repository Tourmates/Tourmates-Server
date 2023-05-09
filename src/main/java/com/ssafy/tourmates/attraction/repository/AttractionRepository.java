package com.ssafy.tourmates.attraction.repository;

import com.ssafy.tourmates.attraction.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<AttractionInfo, Integer>, AttractionRepositoryCustom {
}
