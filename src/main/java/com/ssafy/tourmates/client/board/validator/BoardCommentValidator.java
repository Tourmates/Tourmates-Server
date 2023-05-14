package com.ssafy.tourmates.client.board.validator;

import com.ssafy.tourmates.client.board.BoardComment;
import com.ssafy.tourmates.client.board.repository.BoardCommentRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BoardCommentValidator {

  private final BoardCommentRepository boardCommentRepository;

  public BoardComment findById(Long id) {
    return boardCommentRepository.findById(id)
        .orElseThrow(NoSuchElementException::new);
  }
}
