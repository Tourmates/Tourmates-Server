package com.ssafy.tourmates.client.hashtag.service.impl;

import com.ssafy.tourmates.client.hashtag.Hashtag;
import com.ssafy.tourmates.client.hashtag.repository.HashtagQueryRepository;
import com.ssafy.tourmates.client.hashtag.repository.HashtagRepository;
import com.ssafy.tourmates.client.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagQueryRepository hashtagQueryRepository;

    @Override
    public void registerHashtag(List<String> tagNames) {
        // TODO: 2023/05/24 벌크 삽입 가능
        List<String> existTagNames = hashtagQueryRepository.searchTagNames(tagNames);
        for (String tagName : tagNames) {
            if (existTagNames.contains(tagName)) {
                continue;
            }
            Hashtag hashtag = Hashtag.builder()
                    .tagName(tagName)
                    .build();
            hashtagRepository.save(hashtag);
        }
    }
}
