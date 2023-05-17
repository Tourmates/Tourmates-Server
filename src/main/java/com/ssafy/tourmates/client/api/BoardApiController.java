package com.ssafy.tourmates.client.api;

import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.board.repository.dto.Sort;
import com.ssafy.tourmates.client.board.service.BoardCommentService;
import com.ssafy.tourmates.client.board.service.BoardQueryService;
import com.ssafy.tourmates.client.board.service.BoardService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardDto;
import com.ssafy.tourmates.client.api.dto.board.request.AddBoardCommentRequest;
import com.ssafy.tourmates.client.api.dto.board.request.AddBoardRequest;
import com.ssafy.tourmates.client.api.dto.board.request.EditBoardCommentRequest;
import com.ssafy.tourmates.client.api.dto.board.request.EditBoardRequest;
import com.ssafy.tourmates.client.api.dto.board.response.BoardResponse;
import com.ssafy.tourmates.client.api.dto.board.response.DetailBoardResponse;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
@Api(tags = {"게시판"})
public class BoardApiController {

    private final BoardService boardService;
    private final BoardCommentService boardCommentService;
    private final BoardQueryService boardQueryService;

    @ApiOperation(value = "게시판 조회")
    @GetMapping
    public ResultPage<List<BoardResponse>> searchBoards(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "CREATED_DATE") Sort sort,
            @RequestParam(defaultValue = "1") Integer pageNumber
    ) {
        BoardSearchCondition condition = BoardSearchCondition.builder()
                .keyword(keyword)
                .sort(sort)
                .build();
        PageRequest pageRequest = PageRequest.of(pageNumber / 10, 20);
        List<BoardResponse> boardResponses = boardQueryService.searchByCondition(condition,
                pageRequest);
        log.debug("responses size={}", boardResponses.size());
        log.debug("condition={}", condition);
        log.debug("pageNumber={}", pageNumber);
        return new ResultPage<>(boardResponses);
    }

    @ApiOperation(value = "게시물 총 갯수 조회")
    @GetMapping("/totalCount")
    public Long searchTotalCount(
            @RequestParam(defaultValue = "") String keyword
    ) {
        BoardSearchCondition condition = BoardSearchCondition.builder()
                .keyword(keyword)
                .build();
        Long totalCount = boardQueryService.getTotalCount(condition);
        log.debug("totalCount={}", totalCount);
        return totalCount;
    }

    @ApiOperation(value = "게시판 댓글 등록")
    @GetMapping("/{boardId}/comments/register")
    public Long registerBoardComment(@PathVariable Long boardId,
                                     @Valid @RequestBody AddBoardCommentRequest request) {

        String loginId = SecurityUtil.getCurrentLoginId();

        AddBoardCommentDto dto = AddBoardCommentDto.builder()
                .content(request.getComment())
                .build();

        Long boardCommentId = boardCommentService.registerBoardComment(loginId, boardId, dto);
        return boardCommentId;
    }

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

    @ApiOperation(value = "게시판 상세 조회")
    @GetMapping("/{boardId}")
    public Result<DetailBoardResponse> searchBoard(@PathVariable Long boardId) {
        boardService.increaseHit(boardId);
        DetailBoardResponse response = boardQueryService.searchById(boardId);
        return new Result<>(response);
    }

    @ApiOperation(value = "게시판 수정 조회")
    @GetMapping("/{boardId}/edit")
    public Result<DetailBoardResponse> sss(@PathVariable Long boardId) {
        DetailBoardResponse response = boardQueryService.searchById(boardId);
        return new Result<>(response);
    }

    @ApiOperation(value = "게시판 수정")
    @PostMapping("/{boardId}/edit")
    public Long editBoard(@PathVariable Long boardId, @Valid @RequestBody EditBoardRequest request) {
        EditBoardDto dto = EditBoardDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Long editedBoardId = boardService.editBoard(boardId, dto);
        log.debug("request={}", request);
        log.debug("editedBoardId={}", editedBoardId);
        return editedBoardId;
    }

    @ApiOperation(value = "게시판 삭제")
    @PostMapping("/{boardId}/remove")
    public Long removeBoard(@PathVariable Long boardId) {
        Long removedBoardId = boardService.removeBoard(boardId);
        log.debug("removedBoardId={}", removedBoardId);
        return removedBoardId;
    }

    @ApiOperation(value = "게시판 댓글 수정")
    @PostMapping("/{boardId}/comments/{boardCommentId}/edit")
    public Long editBoardComment(@PathVariable Long boardId,
                                 @PathVariable Long boardCommentId,
                                 @Valid @RequestBody EditBoardCommentRequest request) {

        EditBoardCommentDto dto = EditBoardCommentDto.builder()
                .content(request.getContent())
                .build();

        Long editBoardCommentId = boardCommentService.editBoardComment(boardId, boardCommentId, dto);
        log.debug("editBoardCommentId={}", editBoardCommentId);
        return editBoardCommentId;
    }

    @ApiOperation(value = "게시판 댓글 삭제")
    @GetMapping("/{boardId}/comments/{boardCommentId}/remove")
    public int removeBoardComment(@PathVariable Long boardId, @PathVariable Long boardCommentId) {
        boardCommentService.removeBoardComment(boardCommentId);
        return 1;
    }


    @Data
    @AllArgsConstructor
    static class ResultPage<T> {

        private T data;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {

        private T data;
    }
}
