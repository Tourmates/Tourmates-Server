package com.ssafy.tourmates.admin.api;

import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionResponse;
import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionSearchResponse;
import com.ssafy.tourmates.admin.api.dto.attraction.response.GugunResponse;
import com.ssafy.tourmates.admin.api.dto.attraction.response.SidoResponse;
import com.ssafy.tourmates.admin.attraction.repository.dto.AttractionSearchCondition;
import com.ssafy.tourmates.admin.attraction.service.AttractionQueryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attractions")
public class AttractionApiController {

    private final AttractionQueryService attractionQueryService;

    @GetMapping
    public Result<List<AttractionResponse>> searchByCondition(
            @RequestParam String keyword,
            @RequestParam Integer sidoCode,
            @RequestParam Integer gugunCode,
            @RequestParam(defaultValue = "0") Integer contentTypeId
    ) {
        AttractionSearchCondition condition = AttractionSearchCondition.builder()
                .keyword(keyword)
                .sidoCode(sidoCode)
                .gugunCode(gugunCode)
                .contentTypeId(contentTypeId)
                .build();
        List<AttractionResponse> responses = attractionQueryService.searchByCondition(condition);
        log.debug("responses size={}", responses.size());
        return new Result<>(responses);
    }

    @GetMapping("/sido")
    public Result<?> getSido() {
        List<SidoResponse> responses = attractionQueryService.searchSido();
        log.debug("responses size={}", responses.size());
        return new Result<>(responses);
    }

    @GetMapping("/gugun")
    public Result<?> getGugun(@RequestParam Integer sidoCode) {
        List<GugunResponse> responses = attractionQueryService.searchGugun(sidoCode);
        log.debug("responses size={}", responses.size());
        return new Result<>(responses);
    }

    @GetMapping("/search")
    public Result<List<AttractionSearchResponse>> searchAttraction() {
        List<AttractionSearchResponse> attractionSearchResponses = attractionQueryService.searchAllTitle();
        log.debug("result.size={}", attractionSearchResponses.size());
        return new Result<>(attractionSearchResponses);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
