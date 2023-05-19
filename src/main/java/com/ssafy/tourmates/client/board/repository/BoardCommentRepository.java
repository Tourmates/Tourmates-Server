package com.ssafy.tourmates.client.board.repository;

import com.ssafy.tourmates.client.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findAllByBoardId(@Param("boardId") Long boardId);
}
