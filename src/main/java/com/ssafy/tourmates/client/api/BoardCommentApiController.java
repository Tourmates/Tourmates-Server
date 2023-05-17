package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.api.dto.board.request.AddBoardCommentRequest;
import com.ssafy.tourmates.client.api.dto.board.request.EditBoardCommentRequest;
import com.ssafy.tourmates.client.board.service.BoardCommentService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardCommentDto;
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
@RequestMapping("/boards/{boardId}/comments")
@Api(tags = {"게시판 댓글"})
public class BoardCommentApiController {

    private final BoardCommentService boardCommentService;

    @ApiOperation(value = "게시판 댓글 등록")
    @GetMapping("/register")
    public Long registerBoardComment(
            @PathVariable Long boardId,
            @Valid @RequestBody AddBoardCommentRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();

        AddBoardCommentDto dto = AddBoardCommentDto.builder()
                .content(request.getComment())
                .build();

        Long boardCommentId = boardCommentService.registerBoardComment(loginId, boardId, dto);
        log.debug("boardCommentId={}", boardCommentId);
        return boardCommentId;
    }

    @ApiOperation(value = "게시판 댓글 수정")
    @PostMapping("/{boardCommentId}/edit")
    public Long editBoardComment(
            @PathVariable Long boardId,
            @PathVariable Long boardCommentId,
            @Valid @RequestBody EditBoardCommentRequest request) {

        EditBoardCommentDto dto = EditBoardCommentDto.builder()
                .content(request.getContent())
                .build();

        Long editBoardCommentId = boardCommentService.editBoardComment(boardCommentId, dto);
        log.debug("boardId={}", boardId);
        log.debug("editBoardCommentId={}", editBoardCommentId);
        return editBoardCommentId;
    }

    @ApiOperation(value = "게시판 댓글 삭제")
    @PostMapping("/{boardCommentId}/remove")
    public int removeBoardComment(
            @PathVariable Long boardId,
            @PathVariable Long boardCommentId) {
        Long removedBoardCommentId = boardCommentService.removeBoardComment(boardCommentId);
        log.debug("boardId={}", boardId);
        log.debug("removedBoardCommentId={}", removedBoardCommentId);
        return 1;
    }
}
