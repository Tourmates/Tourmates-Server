package com.ssafy.tourmates.admin.controller;

import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.attraction.service.AttractionService;
import com.ssafy.tourmates.admin.controller.dto.attraction.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attraction")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/search")
    public List<AttractionResponse> searchAttraction(@RequestBody AttractionSearchCondition attractionSearchCondition) {
        return attractionService.searchAttractionByCondition(attractionSearchCondition);
    }

}
