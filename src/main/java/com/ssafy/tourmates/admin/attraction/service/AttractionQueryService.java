package com.ssafy.tourmates.admin.attraction.service;

import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionSearchResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AttractionQueryService {

    List<AttractionSearchResponse> searchAllTitle();
}
