package com.ssafy.tourmates.client.board.repository;

import com.ssafy.tourmates.client.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
