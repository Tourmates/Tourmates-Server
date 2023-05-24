package com.ssafy.tourmates.client.hotplace.validator;

import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class HotPlaceCommentValidator {

    private final HotPlaceCommentRepository hotPlaceCommentRepository;

    public HotPlaceComment findById(Long id){
        return hotPlaceCommentRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<HotPlaceComment> findByHotPlaceId(Long hotPlaceId) {
        return hotPlaceCommentRepository.findAllByHotPlaceId(hotPlaceId);
    }
}
