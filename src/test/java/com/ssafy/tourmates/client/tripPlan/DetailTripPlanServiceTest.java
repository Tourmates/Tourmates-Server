package com.ssafy.tourmates.client.tripPlan;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.attraction.repository.AttractionRepository;
import com.ssafy.tourmates.admin.attraction.validator.AttractionValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanRepository;
import com.ssafy.tourmates.client.tripPlan.service.DetailTripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddDetailTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import com.ssafy.tourmates.client.tripPlan.validator.TripPlanValidator;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.tourmates.client.member.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class DetailTripPlanServiceTest {

    @Autowired
    private TripPlanService tripPlanService;

    @Autowired
    private DetailTripPlanService detailTripPlanService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AttractionValidator attractionValidator;

    @Autowired
    private TripPlanValidator tripPlanValidator;

    @Autowired
    private TripPlanRepository tripPlanRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    private Member savedMember; //멤버
    private AttractionInfo savedAttractionInfo; //관광지
    private TripPlan savedTripPlan;

    @BeforeEach
    void beforeEach() {

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
        savedAttractionInfo = attractionValidator.findById(125266);

        AddTripPlanDto dto = AddTripPlanDto.builder()
                .title("여행계획 제목")
                .build();

        Long tripPlanId = tripPlanService.registerTripPlan(savedMember.getLoginId(), dto);

        savedTripPlan = tripPlanValidator.findById(tripPlanId);

    }

    @Test
    @DisplayName("세부 계획 등록")
    void registerDetailPlan(){

        List<Integer> contentIds = new ArrayList<>();
        contentIds.add(125266);
        contentIds.add(125405);

        //given
        AddDetailTripPlanDto dto = AddDetailTripPlanDto.builder()
                .contentIds(contentIds)
                .build();

        List<Integer> findContentIds = detailTripPlanService.registerDetailTripPlan(savedTripPlan.getId(),dto);

        assertThat(findContentIds.size()).isNotZero();

    }
}
