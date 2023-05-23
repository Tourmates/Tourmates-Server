package com.ssafy.tourmates.admin.attraction.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttractionService {

    Integer increaseReadcount(Integer contentId);
}
