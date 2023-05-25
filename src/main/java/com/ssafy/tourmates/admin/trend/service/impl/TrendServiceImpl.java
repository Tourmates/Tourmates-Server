package com.ssafy.tourmates.admin.trend.service.impl;

import com.ssafy.tourmates.admin.attraction.AttractionInfo;
import com.ssafy.tourmates.admin.trend.TopTrend;
import com.ssafy.tourmates.admin.trend.Trend;
import com.ssafy.tourmates.admin.trend.TrendType;
import com.ssafy.tourmates.admin.trend.repository.TopTrendRepository;
import com.ssafy.tourmates.admin.trend.repository.TrendQueryRepository;
import com.ssafy.tourmates.admin.trend.repository.TrendRepository;
import com.ssafy.tourmates.admin.trend.service.TrendService;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.tourmates.admin.trend.TrendType.*;
import static com.ssafy.tourmates.client.member.Gender.MALE;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrendServiceImpl implements TrendService {

    private final TrendRepository trendRepository;
    private final TrendQueryRepository trendQueryRepository;
    private final TopTrendRepository topTrendRepository;
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

    @Override
    public void registerTopTrend() {
        List<TopTrend> result = new ArrayList<>();

        List<Integer> teenage = trendQueryRepository.searchTop10Teenager();
        for (int i = 0; i < teenage.size(); i++) {
            result.add(TopTrend.builder()
                    .type(TEENAGE)
                    .rank(i + 1)
                    .attractionInfo(AttractionInfo.builder().id(teenage.get(i)).build())
                    .build());
        }

        List<Integer> twenty = trendQueryRepository.searchTop10Twenty();
        for (int i = 0; i < twenty.size(); i++) {
            result.add(TopTrend.builder()
                    .type(TWENTY)
                    .rank(i + 1)
                    .attractionInfo(AttractionInfo.builder().id(twenty.get(i)).build())
                    .build());
        }

        List<Integer> thirty = trendQueryRepository.searchTop10Thirty();
        for (int i = 0; i < thirty.size(); i++) {
            result.add(TopTrend.builder()
                    .type(THIRTY)
                    .rank(i + 1)
                    .attractionInfo(AttractionInfo.builder().id(thirty.get(i)).build())
                    .build());
        }

        List<Integer> male = trendQueryRepository.searchTop10Male();
        for (int i = 0; i < male.size(); i++) {
            result.add(TopTrend.builder()
                    .type(TrendType.MALE)
                    .rank(i + 1)
                    .attractionInfo(AttractionInfo.builder().id(male.get(i)).build())
                    .build());
        }

        List<Integer> female = trendQueryRepository.searchTop10Female();
        for (int i = 0; i < female.size(); i++) {
            result.add(TopTrend.builder()
                    .type(FEMALE)
                    .rank(i + 1)
                    .attractionInfo(AttractionInfo.builder().id(female.get(i)).build())
                    .build());
        }

        List<TopTrend> topTrends = topTrendRepository.saveAll(result);
        log.debug("register top trends={}", topTrends.size());
    }
}
