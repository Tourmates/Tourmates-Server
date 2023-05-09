package com.ssafy.tourmates.hotplace.service;

import com.ssafy.tourmates.hotplace.HotPlace;
import com.ssafy.tourmates.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.member.Member;
import com.ssafy.tourmates.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.tourmates.common.domain.ContentType.ATTRACTION;
import static com.ssafy.tourmates.member.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class HotPlaceServiceTest {

    @Autowired
    private HotPlaceService hotPlaceService;
    @Autowired
    private HotPlaceRepository hotPlaceRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .id(1L)
                .loginId("ssafy1234")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .email("ssafy@ssafy.com")
                .tel("010-1234-1234")
                .birth("2000.01.01")
                .gender(MALE)
                .nickname("ssafy")
                .build();
        savedMember = memberRepository.save(member);
    }

    @Test
    @DisplayName("핫플레이스 등록")
    void registerHotPlace() {
        //given
        AddHotPlaceDto dto = AddHotPlaceDto.builder()
                .tag(ATTRACTION)
                .title("나만의 핫플레이스")
                .content("나만의 핫플레시으 내용")
                .contentId(125266)
                .visitedDate("2020-01-01")
                .uploadFiles(null)
                .build();

        //when
        Long hotPlaceId = hotPlaceService.registerHotPlace(savedMember.getLoginId(), dto);

        //then
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        assertThat(findHotPlace).isPresent();
    }
}
