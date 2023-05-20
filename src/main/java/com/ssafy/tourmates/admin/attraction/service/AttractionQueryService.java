package com.ssafy.tourmates.admin.attraction.service;

import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.controller.dto.attraction.response.AttractionResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AttractionQueryService {

    List<AttractionResponse> searchByCondition(AttractionSearchCondition attractionSearchCondition);
}
