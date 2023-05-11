package com.ssafy.tourmates.admin.attraction.repository;

import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.controller.dto.attraction.response.AttractionResponse;

import java.util.List;

public interface AttractionRepositoryCustom {

    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);
}
