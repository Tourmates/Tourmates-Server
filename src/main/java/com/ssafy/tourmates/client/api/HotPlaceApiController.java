package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.hotplace.response.EditHotPlaceResponse;
import com.ssafy.tourmates.client.hotplace.service.*;
import com.ssafy.tourmates.client.hotplace.service.dto.*;
import com.ssafy.tourmates.common.FileStore;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.common.domain.UploadFile;
import com.ssafy.tourmates.client.api.dto.hotplace.request.AddHotPlaceRequest;
import com.ssafy.tourmates.client.api.dto.hotplace.request.EditHotPlaceRequest;
import com.ssafy.tourmates.client.api.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.client.api.dto.hotplace.response.HotPlaceResponse;
import com.ssafy.tourmates.client.hotplace.repository.dto.HotPlaceSearchCondition;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hotPlaces")
@Api(tags = {"핫플레이스"})
public class HotPlaceApiController {

    private final HotPlaceService hotPlaceService;
    private final HotPlaceQueryService hotPlaceQueryService;
    private final HotPlaceLikeService hotPlaceLikeService;
    private final FileStore fileStore;

    @ApiOperation(value = "핫플레이스 등록")
    @PostMapping("/register")
    public Long registerHotPlace(@Valid AddHotPlaceRequest request) throws IOException {
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

    @ApiOperation(value = "핫플레이스 좋아요 등록")
    @PostMapping("/{hotPlaceId}/like/register")
    public Long registerHotPlaceLike(@PathVariable Long hotPlaceId) {

        String loginId = SecurityUtil.getCurrentLoginId();

        Long hotPlaceLikeId = hotPlaceLikeService.registerHotPlaceLike(loginId, hotPlaceId);
        return hotPlaceLikeId;
    }

    @ApiOperation(value = "핫플레이스 조회")
    @GetMapping
    public Result<List<HotPlaceResponse>> searchHotPlaces(
            @RequestParam(required = false) List<ContentType> tags,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        List<ContentType> contentTypes = new ArrayList<>();
        if (tags != null) {
            contentTypes.addAll(tags);
        }

        HotPlaceSearchCondition condition = HotPlaceSearchCondition.builder()
                .tags(contentTypes)
                .keyword(keyword)
                .build();

        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 10);
        List<HotPlaceResponse> responses = hotPlaceQueryService.searchByCondition(condition, pageRequest);
        log.debug("size={}", responses.size());
        return new Result<>(responses);
    }

    @ApiOperation(value = "핫플레이스 총 갯수 조회")
    @GetMapping("/totalCount")
    public Long totalCount(
            @RequestParam(required = false) List<ContentType> tags,
            @RequestParam(defaultValue = "") String keyword
    ) {
        List<ContentType> contentTypes = new ArrayList<>();
        if (tags != null) {
            contentTypes.addAll(tags);
        }

        HotPlaceSearchCondition condition = HotPlaceSearchCondition.builder()
                .tags(contentTypes)
                .keyword(keyword)
                .build();

        return hotPlaceQueryService.getTotalCount(condition);
    }

    @ApiOperation(value = "핫플레이스 상세조회")
    @GetMapping("/{hotPlaceId}")
    public Result<?> searchHotPlace(@PathVariable Long hotPlaceId) {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        hotPlaceService.increaseHit(hotPlaceId);
        DetailHotPlaceResponse response = hotPlaceQueryService.searchById(loginId, hotPlaceId);
        log.debug("hotPlaceId={}", response.getHotPlaceId());
        return new Result<>(response);
    }

    @ApiOperation(value = "핫플레이스 수정 조회")
    @GetMapping("/{hotPlaceId}/edit")
    public Result<?> editHotPlace(@PathVariable Long hotPlaceId) {
        EditHotPlaceResponse response = hotPlaceQueryService.searchEditById(hotPlaceId);
        log.debug("response={}", response.getHotPlaceId());
        return new Result<>(response);
    }

    @ApiOperation(value = "핫플레이스 수정")
    @PostMapping("/{hotPlaceId}/edit")
    public Long editHotPlace(
            @PathVariable Long hotPlaceId,
            @Valid EditHotPlaceRequest request) throws IOException {
        List<UploadFile> files = fileStore.storeFiles(request.getFiles());

        EditHotPlaceDto dto = EditHotPlaceDto.builder()
                .tag(request.getTag())
                .title(request.getTitle())
                .content(request.getContent())
                .visitedDate(request.getVisitedDate())
                .uploadFiles(files)
                .build();

        Long editHotPlaceId = hotPlaceService.editHotPlace(hotPlaceId, dto);
        log.debug("request={}", request);
        log.debug("editHotPlaceId={}", editHotPlaceId);
        return editHotPlaceId;
    }

    @ApiOperation(value = "핫플레이스 삭제")
    @PostMapping("/{hotPlaceId}/remove")
    public int removeHotPlace(@PathVariable Long hotPlaceId) {
        Long removeHotPlaceId = hotPlaceService.removeHotPlace(hotPlaceId);
        log.debug("removeHotPlaceId={}", removeHotPlaceId);
        return 1;
    }

    @ApiOperation(value = "핫플레이스 좋아요 삭제")
    @GetMapping("/{hotPlaceId}/likes/{likeId}/remove")
    public void removeHotPlaceLike(@PathVariable Long hotPlaceId, @PathVariable Long likeId) {
        hotPlaceLikeService.removeHotPlaceLike(likeId);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
