package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.controller.dto.hotplace.request.AddHotplaceCommentRequest;
import com.ssafy.tourmates.client.controller.dto.hotplace.request.EditHotPlaceCommentRequest;
import com.ssafy.tourmates.client.hotplace.service.*;
import com.ssafy.tourmates.client.hotplace.service.dto.*;
import com.ssafy.tourmates.common.FileStore;
import com.ssafy.tourmates.common.domain.ContentType;
import com.ssafy.tourmates.common.domain.UploadFile;
import com.ssafy.tourmates.client.controller.dto.hotplace.request.AddHotPlaceRequest;
import com.ssafy.tourmates.client.controller.dto.hotplace.request.EditHotPlaceRequest;
import com.ssafy.tourmates.client.controller.dto.hotplace.response.DetailHotPlaceResponse;
import com.ssafy.tourmates.client.controller.dto.hotplace.response.HotPlaceResponse;
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
public class HotPlaceController {

    private final HotPlaceService hotPlaceService;
    private final HotPlaceQueryService hotPlaceQueryService;
    private final HotPlaceCommentService hotPlaceCommentService;
    private final HotPlaceLikeService hotPlaceLikeService;
    private final HotPlaceCommentQueryService hotPlaceCommentQueryService;
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

    @ApiOperation(value = "핫플레이스 댓글 등록")
    @PostMapping("/{hotPlaceId}/comments/register")
    public Long registerHotplaceComment(@PathVariable Long hotPlaceId, @Valid @RequestBody AddHotplaceCommentRequest request){

        String loginId = SecurityUtil.getCurrentLoginId();

        AddHotPlaceCommentDto dto = AddHotPlaceCommentDto.builder()
                .content(request.getComment())
                .build();

        Long hotplaceCommentId = hotPlaceCommentService.registerHotplaceComment(loginId,hotPlaceId, dto);
        return hotplaceCommentId;
    }

    @ApiOperation(value = "핫플레이스 좋아요 등록")
    @PostMapping("/{hotPlaceId}/like/register")
    public Long registerHotPlaceLike(@PathVariable Long hotPlaceId){

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
        return new ResultPage<>(responses, pageNumber, 10);
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

    @ApiOperation(value = "핫플레이스 댓글 수정")
    @PostMapping("/{hotPlaceId}/comments/{hotPlaceCommentId}/edit")
    public Long editHotPlaceComment(@PathVariable Long hotPlaceId, @PathVariable Long hotPlaceCommentId, @Valid @RequestBody EditHotPlaceCommentRequest request){

        EditHotPlaceCommentDto dto = EditHotPlaceCommentDto.builder()
                .content(request.getContent())
                .build();

        Long editHotPlaceCommentId = hotPlaceCommentService.editHotPlaceComment(hotPlaceId, hotPlaceCommentId, dto);
        return editHotPlaceCommentId;
    }

    @ApiOperation(value = "핫플레이스 삭제")
    @PostMapping("/{hotPlaceId}/remove")
    public int removeHotPlace(@PathVariable Long hotPlaceId) {
        Long removeHotPlaceId = hotPlaceService.removeHotPlace(hotPlaceId);
        log.debug("removeHotPlaceId={}", removeHotPlaceId);
        return 1;
    }

    @ApiOperation(value = "핫플레이스 댓글 삭제")
    @GetMapping("/{hotPlaceId}/comments/{commentId}/remove")
    public void removeHotPlaceComment(@PathVariable Long hotPlaceId, @PathVariable Long commentId){
       hotPlaceCommentService.removeHotPlaceComment(commentId);
    }


    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private int pageNumber;
        private int pageSize;
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
