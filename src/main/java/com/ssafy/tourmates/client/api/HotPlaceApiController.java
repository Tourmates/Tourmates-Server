package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.hotplace.request.AddHotplaceCommentRequest;
import com.ssafy.tourmates.client.api.dto.hotplace.request.EditHotPlaceCommentRequest;
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
        return new ResultPage<>(responses);
    }

    @ApiOperation(value = "핫플레이스 상세조회")
    @GetMapping("/{hotPlaceId}")
    public Response searchHotPlace(@PathVariable Long hotPlaceId) {
        DetailHotPlaceResponse response = hotPlaceQueryService.searchById(hotPlaceId);
        ResponseData responseData = new ResponseData(response);
        return new Response(responseData);
    }

    @ApiOperation(value = "핫플레이스 수정")
    @PostMapping("/{hotPlaceId}/edit")
    public Long editHotPlace(@PathVariable Long hotPlaceId, @Valid @RequestBody EditHotPlaceRequest request) throws IOException {
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
    static class ResultPage<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class Response {
        private ResponseData data;
    }

    @Data
    @AllArgsConstructor
    static class ResponseData {
        private DetailHotPlaceResponse detailHotPlaceResponse;
    }
}
