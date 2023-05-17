package com.ssafy.tourmates.admin.attraction.service.impl;

import com.ssafy.tourmates.admin.attraction.repository.AttractionRepository;
import com.ssafy.tourmates.admin.attraction.repository.AttractionRepositoryCustom;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.attraction.service.AttractionQueryService;
import com.ssafy.tourmates.admin.controller.dto.attraction.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionQueryService {

    private final AttractionRepository attractionRepository;

    @Override
    public List<AttractionResponse> searchByCondition(AttractionSearchCondition attractionSearchCondition) {
        return attractionRepository.searchByCondition(attractionSearchCondition);
    }
}
