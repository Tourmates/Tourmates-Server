package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.common.FileStore;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.common.domain.UploadFile;
import com.ssafy.tourmates.controller.dto.hotplace.request.AddHotPlaceRequest;
import com.ssafy.tourmates.controller.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.controller.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.hotplace.repository.dto.HotPlaceSearchCondition;
import com.ssafy.tourmates.hotplace.service.HotPlaceQueryService;
import com.ssafy.tourmates.hotplace.service.HotPlaceService;
import com.ssafy.tourmates.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hotPlaces")
@Api(tags = {"핫플레이스"})
public class HotPlaceController {

    private final HotPlaceService hotPlaceService;
    private final HotPlaceQueryService hotPlaceQueryService;
    private final FileStore fileStore;

    @ApiOperation(value = "핫플레이스 등록")
    @PostMapping("/register")
    public Long registerHotPlace(@Valid @RequestBody AddHotPlaceRequest request) throws IOException {
        String loginId = SecurityUtil.getCurrentLoginId();
        List<UploadFile> uploadFiles = fileStore.storeFiles(request.getFiles());

        AddHotPlaceDto dto = AddHotPlaceDto.builder()
                .contentId(request.getContentId())
                .tag(request.getTag())
                .title(request.getTitle())
                .content(request.getContent())
                .visitedDate(request.getVisitedDate())
                .uploadFiles(uploadFiles)
                .build();

        Long hotPlaceId = hotPlaceService.registerHotPlace(loginId, dto);
        log.debug("hotPlaceId={}", hotPlaceId);
        return hotPlaceId;
    }

    @GetMapping
    public ResultPage<List<HotPlaceResponse>> searchHotPlaces(
            @RequestParam(defaultValue = "") ContentType tag,
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        HotPlaceSearchCondition condition = HotPlaceSearchCondition.builder()
                .tag(tag)
                .title(title)
                .content(content)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        List<HotPlaceResponse> responses = hotPlaceQueryService.searchByCondition(condition, pageRequest);
        return new ResultPage<>(responses, pageNumber, 10);
    }

    @GetMapping("/{hotPlaceId}")
    public DetailHotPlaceResponse searchHotPlace(@PathVariable Long hotPlaceId) {
        DetailHotPlaceResponse response = hotPlaceQueryService.searchById(hotPlaceId);
        return response;
    }

    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
    }
}
