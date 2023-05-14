package com.ssafy.tourmates.client.board.service;

import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardCommentService {

  Long registerBoardComment(String loginId, Long hotplaceId, AddBoardCommentDto dto);

}
