package com.ssafy.tourmates.admin.api;

import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionSearchResponse;
import com.ssafy.tourmates.admin.attraction.service.AttractionQueryService;
import com.ssafy.tourmates.admin.api.dto.attraction.response.AttractionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/attractions")
public class AttractionApiController {

    private final AttractionQueryService attractionQueryService;

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
