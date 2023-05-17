package com.ssafy.tourmates.client.board.service;

import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardCommentService {

    Long registerBoardComment(String loginId, Long boardId, AddBoardCommentDto dto);

    Long editBoardComment(Long boardCommentId, EditBoardCommentDto dto);

    Long removeBoardComment(Long boardCommentId);
}
