package com.ssafy.tourmates.admin.attraction.repository;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<AttractionInfo, Long>, AttractionRepositoryCustom {
}
