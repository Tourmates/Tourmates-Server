package com.ssafy.tourmates.admin.attraction.service;

import com.ssafy.tourmates.admin.api.dto.attraction.response.*;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AttractionQueryService {

    List<AttractionResponse> searchByCondition(AttractionSearchCondition condition);

    List<AttractionSearchResponse> searchAllTitle();

    List<SidoResponse> searchSido();

    List<GugunResponse> searchGugun(Integer sidoCode);

    List<AttractionTripPlanResponse> searchTripPlanAttraction(String keyword);
}
