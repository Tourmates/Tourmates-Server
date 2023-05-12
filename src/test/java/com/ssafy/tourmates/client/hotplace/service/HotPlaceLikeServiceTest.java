package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceLikeRepository;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceCommentValidator;
import com.ssafy.tourmates.client.hotplace.validator.HotPlaceValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;
import static com.ssafy.tourmates.client.member.Gender.MALE;
import static com.ssafy.tourmates.common.domain.ContentType.ATTRACTION;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class HotPlaceLikeServiceTest {

    @Autowired
    private HotPlaceCommentService hotPlaceCommentService;

    @Autowired
    private HotPlaceLikeService hotPlaceLikeService;

    @Autowired
    private HotPlaceLikeRepository hotPlaceLikeRepository;

    @Autowired
    private HotPlaceCommentRepository hotPlaceCommentRepository;

    @Autowired
    private MemberValidator memberValidator;

    @Autowired
    private HotPlaceValidator hotPlaceValidator;

    @Autowired
    private HotPlaceCommentValidator hotPlaceCommentValidator;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HotPlaceRepository hotPlaceRepository;

    private Member savedMember;
    private HotPlace savedHotPlace;
    private AttractionInfo attractionInfo;

    @BeforeEach
    void beforeEach(){
        Member member = Member.builder()
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

        HotPlace hotPlace = HotPlace.builder()
                .tag(ATTRACTION)
                .title("제목1")
                .content("내용1")
                .hit(1)
                .visitedDate("20221201")
                .active(ACTIVE)
                .member(savedMember)
                .build();

        savedHotPlace = hotPlaceRepository.save(hotPlace);
    }

    @Test
    @DisplayName("좋아요 등록")
    void registerHotPlaceLike(){
        //when
        Long hotPlaceLikeId = hotPlaceLikeService.registerHotPlaceLike(savedMember.getLoginId(), savedHotPlace.getId());

        //then
        assertThat(hotPlaceLikeRepository.findById(hotPlaceLikeId)).isPresent();
    }
}
