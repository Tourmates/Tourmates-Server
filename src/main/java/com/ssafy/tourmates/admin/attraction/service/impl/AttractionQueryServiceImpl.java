package com.ssafy.tourmates.admin.attraction.service.impl;

import com.ssafy.tourmates.admin.api.dto.attraction.response.*;
import com.ssafy.tourmates.admin.attraction.repository.AttractionQueryRepository;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.attraction.service.AttractionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionQueryService {

    private final AttractionQueryRepository attractionQueryRepository;

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition condition) {
        return attractionQueryRepository.searchByCondition(condition);
    }

    @Override
    public List<AttractionSearchResponse> searchAllTitle() {
        return attractionQueryRepository.searchAllTitle();
    }

    @Override
    public List<SidoResponse> searchSido() {
        return attractionQueryRepository.searchSido();
    }

    @Override
    public List<GugunResponse> searchGugun(Integer sidoCode) {
        return attractionQueryRepository.searchGugun(sidoCode);
    }

    @Override
    public List<AttractionTripPlanResponse> searchTripPlanAttraction(String keyword) {
        return attractionQueryRepository.searchTripPlanAttraction(keyword);
    }
}
