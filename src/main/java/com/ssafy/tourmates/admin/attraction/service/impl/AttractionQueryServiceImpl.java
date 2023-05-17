package com.ssafy.tourmates.admin.attraction.service.impl;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.attraction.repository.AttractionRepositoryCustom;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.attraction.service.AttractionService;
import com.ssafy.tourmates.admin.controller.dto.attraction.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionQueryServiceImpl implements AttractionService {

    private final AttractionRepositoryCustom attractionRepositoryCustom;
    @Override
    public List<AttractionResponse> searchAttractionByCondition(AttractionSearchCondition attractionSearchCondition) {
        return attractionRepositoryCustom.searchByCondition(attractionSearchCondition);
    }
}
