package com.ssafy.tourmates.admin.attraction.service.impl;

import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionSearchResponse;
import com.ssafy.tourmates.admin.attraction.repository.AttractionQueryRepository;
import com.ssafy.tourmates.admin.attraction.service.AttractionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionQueryService {

    private final AttractionQueryRepository attractionQueryRepository;

    @Override
    public List<AttractionSearchResponse> searchAllTitle() {
        return attractionQueryRepository.searchAllTitle();
    }
}
