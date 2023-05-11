package com.ssafy.tourmates.client.board.service;

import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    Long registerBoard(String loginId, AddBoardDto dto);
}
