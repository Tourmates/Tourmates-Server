package com.ssafy.tourmates.client.board.service.impl;

import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.board.service.BoardQueryService;
import com.ssafy.tourmates.client.controller.dto.board.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService {

    private final BoardQueryService boardQueryService;

    @Override
    public List<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable) {
        return boardQueryService.searchByCondition(condition, pageable);
    }
}
