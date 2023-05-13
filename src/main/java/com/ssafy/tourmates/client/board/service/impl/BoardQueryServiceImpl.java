package com.ssafy.tourmates.client.board.service.impl;

import com.ssafy.tourmates.client.board.repository.BoardQueryRepository;
import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.board.service.BoardQueryService;
import com.ssafy.tourmates.client.controller.dto.board.response.BoardResponse;
import com.ssafy.tourmates.client.controller.dto.board.response.DetailBoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService {

    private final BoardQueryRepository boardQueryRepository;

    @Override
    public Page<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable) {
        return boardQueryRepository.searchByCondition(condition, pageable);
    }

    @Override
    public DetailBoardResponse searchById(Long boardId) {
        return boardQueryRepository.searchBoard(boardId);
    }
}
