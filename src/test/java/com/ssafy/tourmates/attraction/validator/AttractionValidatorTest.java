package com.ssafy.tourmates.attraction.validator;

import com.ssafy.tourmates.attraction.AttractionInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AttractionValidatorTest {

    @Autowired
    private AttractionValidator attractionValidator;

    @Test
    @DisplayName("관광지 PK 조회")
    void findById() {
        //given
        int contentId = 125266;

        //when
        AttractionInfo findAttractionInfo = attractionValidator.findById(contentId);

        //then
        assertThat(findAttractionInfo.getId()).isEqualTo(contentId);
    }
}