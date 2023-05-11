package com.ssafy.tourmates.client.board.service;

import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    Long registerBoard(String loginId, AddBoardDto dto);

    Long editBoard(Long boardId, EditBoardDto dto);

    Long removeBoard(Long boardId);
}
