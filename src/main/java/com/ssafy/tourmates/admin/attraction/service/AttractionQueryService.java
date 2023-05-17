package com.ssafy.tourmates.admin.attraction.service;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AttractionQueryService {

    List<AttractionInfo> searchByCondition(AttractionSearchCondition attractionSearchCondition);
}
