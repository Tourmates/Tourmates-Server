package com.ssafy.tourmates.hotplace.validator;

import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.hotplace.HotPlace;
import com.ssafy.tourmates.hotplace.repository.HotPlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class HotPlaceValidatorTest {

    @Autowired
    private HotPlaceValidator hotPlaceValidator;
    @Autowired
    private HotPlaceRepository hotPlaceRepository;

    private HotPlace savedHotPlace;

    @BeforeEach
    void beforeEach() {
        HotPlace hotPlace = HotPlace.builder()
                .id(1L)
                .tag(ContentType.ATTRACTION)
                .title("나만의 핫플레이스")
                .content("재밌습니다")
                .visitedDate("2020-01-01")
                .build();
        savedHotPlace = hotPlaceRepository.save(hotPlace);
    }

    @Test
    @DisplayName("핫플레이스 PK 조회")
    void findById() {
        //given

        //when
        HotPlace findHotPlace = hotPlaceValidator.findById(savedHotPlace.getId());

        //then
        assertThat(findHotPlace.getId()).isEqualTo(savedHotPlace.getId());
    }
}