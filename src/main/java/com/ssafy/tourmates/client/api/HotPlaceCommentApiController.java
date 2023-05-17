package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.hotplace.request.AddHotplaceCommentRequest;
import com.ssafy.tourmates.client.api.dto.hotplace.request.EditHotPlaceCommentRequest;
import com.ssafy.tourmates.client.hotplace.service.HotPlaceCommentService;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.tourmates.client.hotplace.service.dto.EditHotPlaceCommentDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("hotPlaces/{hotPlaceId}/comments")
@Api(tags = {"핫플레이스 댓글"})
public class HotPlaceCommentApiController {

    private final HotPlaceCommentService hotPlaceCommentService;

    @ApiOperation(value = "핫플레이스 댓글 등록")
    @PostMapping("/register")
    public Long registerHotPlaceComment(
            @PathVariable Long hotPlaceId,
            @Valid @RequestBody AddHotplaceCommentRequest request) {

        String loginId = SecurityUtil.getCurrentLoginId();

        AddHotPlaceCommentDto dto = AddHotPlaceCommentDto.builder()
                .content(request.getComment())
                .build();

        Long hotPlaceCommentId = hotPlaceCommentService.registerHotPlaceComment(loginId, hotPlaceId, dto);
        log.debug("savedHotPlaceCommentId={}", hotPlaceCommentId);
        return hotPlaceCommentId;
    }

    @ApiOperation(value = "핫플레이스 댓글 수정")
    @PostMapping("/{hotPlaceCommentId}/edit")
    public Long editHotPlaceComment(
            @PathVariable Long hotPlaceId,
            @PathVariable Long hotPlaceCommentId,
            @Valid @RequestBody EditHotPlaceCommentRequest request) {

        EditHotPlaceCommentDto dto = EditHotPlaceCommentDto.builder()
                .content(request.getContent())
                .build();

        Long editedHotPlaceCommentId = hotPlaceCommentService.editHotPlaceComment(hotPlaceCommentId, dto);
        log.debug("hotPlaceId={}", hotPlaceId);
        log.debug("editedHotPlaceCommentId={}", editedHotPlaceCommentId);
        return editedHotPlaceCommentId;
    }

    @ApiOperation(value = "핫플레이스 댓글 삭제")
    @GetMapping("/{hotPlaceCommentId}/remove")
    public int removeHotPlaceComment(
            @PathVariable Long hotPlaceId,
            @PathVariable Long hotPlaceCommentId) {
        log.debug("hotPlaceId={}", hotPlaceId);
        Long removedHotPlaceCommentId = hotPlaceCommentService.removeHotPlaceComment(hotPlaceCommentId);
        log.debug("removedHotPlaceCommentId={}", removedHotPlaceCommentId);
        return 1;
    }
}
