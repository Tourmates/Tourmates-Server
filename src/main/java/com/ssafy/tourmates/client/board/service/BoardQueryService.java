package com.ssafy.tourmates.client.board.service;

import com.ssafy.tourmates.client.board.repository.dto.BoardSearchCondition;
import com.ssafy.tourmates.client.controller.dto.board.response.BoardResponse;
import com.ssafy.tourmates.client.controller.dto.board.response.DetailBoardResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BoardQueryService {

    List<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable);

    DetailBoardResponse searchById(Long boardId);
}
