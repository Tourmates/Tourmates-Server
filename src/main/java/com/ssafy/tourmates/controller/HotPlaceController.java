package com.ssafy.tourmates.controller;

import com.ssafy.tourmates.common.FileStore;
import com.ssafy.tourmates.common.domain.UploadFile;
import com.ssafy.tourmates.controller.dto.hotplace.request.AddHotPlaceRequest;
import com.ssafy.tourmates.hotplace.service.HotPlaceService;
import com.ssafy.tourmates.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
