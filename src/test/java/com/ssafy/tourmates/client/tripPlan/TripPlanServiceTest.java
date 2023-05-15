package com.ssafy.tourmates.client.tripPlan;

import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import com.ssafy.tourmates.client.tripPlan.repository.TripPlanRepository;
import com.ssafy.tourmates.client.tripPlan.service.TripPlanService;
import com.ssafy.tourmates.client.tripPlan.service.dto.AddTripPlanDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.tourmates.client.member.Gender.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class TripPlanServiceTest {

    @Autowired
    private TripPlanService tripPlanService;
    @Autowired
    private TripPlanRepository tripPlanRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

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
    }

    @Test
    @DisplayName("여행계획 등록")
    @Rollback(value = false)
    void registerTripPlan(){
        //given
        AddTripPlanDto dto = AddTripPlanDto.builder()
                .title("여행계획 제목")
                .build();

        //when
        Long tripPlanId = tripPlanService.registerTripPlan(savedMember.getLoginId(), dto);

        //then
        Optional<TripPlan> findTripPlan = tripPlanRepository.findById(tripPlanId);
        assertThat(findTripPlan).isPresent();
    }



}
