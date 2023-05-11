package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.service.BoardService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import com.ssafy.tourmates.client.controller.dto.board.request.AddBoardRequest;
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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
@Api(tags = {"게시판"})
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시판 등록")
    @PostMapping("/register")
    public Long registerBoard(@Valid @RequestBody AddBoardRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        AddBoardDto dto = AddBoardDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Long savedBoardId = boardService.registerBoard(loginId, dto);
        log.debug("request={}", request);
        log.debug("savedBoardId={}", savedBoardId);
        return savedBoardId;
    }
}
