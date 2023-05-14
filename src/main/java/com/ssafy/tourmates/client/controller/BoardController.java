package com.ssafy.tourmates.client.controller;

import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.board.repository.dto.Sort;
import com.ssafy.tourmates.client.board.service.BoardQueryService;
import com.ssafy.tourmates.client.board.service.BoardService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardDto;
import com.ssafy.tourmates.client.controller.dto.board.request.AddBoardCommentRequest;
import com.ssafy.tourmates.client.controller.dto.board.request.AddBoardRequest;
import com.ssafy.tourmates.client.controller.dto.board.request.EditBoardRequest;
import com.ssafy.tourmates.client.controller.dto.board.response.BoardResponse;
import com.ssafy.tourmates.client.controller.dto.board.response.DetailBoardResponse;
import com.ssafy.tourmates.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.tourmates.common.PageDto;
import com.ssafy.tourmates.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
@Api(tags = {"게시판"})
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class BoardController {

    private final BoardService boardService;
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
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 20);
        Page<BoardResponse> responses = boardQueryService.searchByCondition(condition, pageRequest);
        log.debug("responses size={}", responses.getContent().size());
        log.debug("condition={}", condition);
        log.debug("pageNumber={}", pageNumber);
        return new ResultPage<>(responses.getContent(), new PageDto(pageNumber, 20, responses.getTotalElements()));
    }

    @ApiOperation(value = "게시판 댓글 등록")
    @GetMapping("/{boardId}/comments/register")
    public Long registerBoardComment(@PathVariable Long boardId, @Valid @RequestBody AddBoardCommentRequest request){

        String loginId = SecurityUtil.getCurrentLoginId();

        AddBoardCommentDto dto = AddBoardCommentDto.builder()
            .content(request.getComment())
            .build();


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



    @Data
    @AllArgsConstructor
    static class ResultPage<T> {
        private T data;
        private PageDto page;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
