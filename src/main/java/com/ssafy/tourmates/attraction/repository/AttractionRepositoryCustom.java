package com.ssafy.tourmates.attraction.repository;

import com.ssafy.tourmates.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.controller.dto.attraction.response.AttractionResponse;

import java.util.List;

public interface AttractionRepositoryCustom {

    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
}
