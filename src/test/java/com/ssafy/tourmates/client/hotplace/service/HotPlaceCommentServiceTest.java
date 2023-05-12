package com.ssafy.tourmates.client.hotplace.service;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.HotPlaceComment;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.tourmates.client.hotplace.service.dto.EditHotPlaceCommentDto;
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

import java.util.Optional;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;
import static com.ssafy.tourmates.client.member.Gender.MALE;
import static com.ssafy.tourmates.common.domain.ContentType.ATTRACTION;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class HotPlaceCommentServiceTest {

    @Autowired
    private HotPlaceCommentService hotPlaceCommentService;

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
    @DisplayName("댓글 등록")
    void registerHotPlaceComment(){
        //given
        AddHotPlaceCommentDto addHotPlaceCommentDto = AddHotPlaceCommentDto.builder()
                .content("내용1")
                .build();

        //when
        Long hotPlaceCommentId = hotPlaceCommentService.registerHotplaceComment(savedMember.getLoginId(), savedHotPlace.getId(), addHotPlaceCommentDto);

        //then
        Optional<HotPlaceComment> findHotPlaceComment = hotPlaceCommentRepository.findById(hotPlaceCommentId);
        assertThat(findHotPlaceComment).isPresent();
    }

    @Test
    @DisplayName("댓글 수정")
    void editHotPlaceCommenet(){
        //given
        EditHotPlaceCommentDto editHotPlaceCommentDto = EditHotPlaceCommentDto.builder()
                .content("수정된 내용")
                .build();

        HotPlaceComment savedHotPlaceComment = HotPlaceComment.builder()
                .content("원본 내용")
                .build();

        HotPlaceComment originalHotPlaceComment = hotPlaceCommentRepository.save(savedHotPlaceComment);

        //when
        Long hotPlaceCommentId = hotPlaceCommentService.editHotPlaceComment(savedMember.getId(), originalHotPlaceComment.getId(), editHotPlaceCommentDto);

        //then
        Optional<HotPlaceComment> editHotPlaceComment = hotPlaceCommentRepository.findById(hotPlaceCommentId);

        assertThat(editHotPlaceComment).isPresent();
        assertThat(editHotPlaceComment.get().getContent()).isEqualTo(editHotPlaceCommentDto.getContent());
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteHotPlaceComment(){

        //given
        HotPlaceComment newHotPlaceComment = HotPlaceComment.builder()
                .content("원본 내용")
                .build();

        HotPlaceComment savedHotPlaceComment = hotPlaceCommentRepository.save(newHotPlaceComment);

        //when
        hotPlaceCommentService.removeHotPlaceComment(savedHotPlaceComment.getId());

        //then
        Optional<HotPlaceComment> removedHotPlaceComment = hotPlaceCommentRepository.findById(savedHotPlaceComment.getId());

        assertThat(removedHotPlaceComment).isNotPresent();
    }



}
