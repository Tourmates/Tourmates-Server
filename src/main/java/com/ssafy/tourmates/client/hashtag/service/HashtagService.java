package com.ssafy.tourmates.client.hashtag.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HashtagService {

    void registerHashtag(List<String> tagNames);
}
