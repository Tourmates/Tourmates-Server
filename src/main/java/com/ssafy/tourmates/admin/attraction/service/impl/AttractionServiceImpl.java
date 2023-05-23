package com.ssafy.tourmates.admin.attraction.service.impl;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.attraction.repository.AttractionRepository;
import com.ssafy.tourmates.admin.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;


    @Override
    public Integer increaseReadcount(Integer contentId) {
        AttractionInfo attractionInfo = attractionRepository.findById(contentId)
                .orElseThrow(NoSuchElementException::new);

        attractionInfo.increaseReadcount();
        return attractionInfo.getId();
    }
}
