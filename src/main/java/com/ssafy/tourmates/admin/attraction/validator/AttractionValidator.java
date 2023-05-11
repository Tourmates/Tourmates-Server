package com.ssafy.tourmates.admin.attraction.validator;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.attraction.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class AttractionValidator {

    private final AttractionRepository attractionRepository;

    public AttractionInfo findById(Integer id) {
        return attractionRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
