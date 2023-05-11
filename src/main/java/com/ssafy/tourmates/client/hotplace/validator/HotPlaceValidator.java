package com.ssafy.tourmates.client.hotplace.validator;

import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class HotPlaceValidator {

    private final HotPlaceRepository hotPlaceRepository;

    public HotPlace findById(Long id) {
        return hotPlaceRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
