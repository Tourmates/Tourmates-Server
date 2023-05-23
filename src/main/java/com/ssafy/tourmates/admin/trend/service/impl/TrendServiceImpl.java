package com.ssafy.tourmates.admin.trend.service.impl;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.trend.Trend;
import com.ssafy.tourmates.admin.trend.repository.TrendRepository;
import com.ssafy.tourmates.admin.trend.service.TrendService;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ssafy.tourmates.client.member.Gender.MALE;

@Service
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService {

    private final TrendRepository trendRepository;
    private final MemberValidator memberValidator;

    @Override
    public Long registerTrend(Integer contentId) {
        Trend trend = Trend.builder()
                .attractionInfo(AttractionInfo.builder().id(contentId).build())
                .build();
        Trend savedTrend = trendRepository.save(trend);
        return savedTrend.getId();
    }

    @Override
    public Long increaseTrend(String loginId, Integer contentId) {
        Trend trend = null;
        Optional<Trend> findTrend = trendRepository.findByAttractionInfoId(contentId);
        if (!findTrend.isPresent()) {
            trend = trendRepository.save(Trend.builder()
                    .attractionInfo(AttractionInfo.builder().id(contentId).build())
                    .build());
        } else {
            trend = findTrend.get();
        }

        Member member = memberValidator.findByLoginId(loginId);

        if (member.getGender() == MALE) {
            trend.increaseMale();
        } else {
            trend.increaseFemale();
        }

        String birth = member.getBirth().split("-")[0];
        int year = LocalDateTime.now().getYear();
        int age = year - Integer.parseInt(birth) + 1;

        switch (age / 10) {
            case 1:
                trend.increaseTeenage();
                break;
            case 2:
                trend.increaseTwenty();
                break;
            case 3:
                trend.increaseThirty();
                break;
        }

        return trend.getId();
    }

}
