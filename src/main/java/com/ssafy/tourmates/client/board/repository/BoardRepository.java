package com.ssafy.tourmates.client.board.repository;

import com.ssafy.tourmates.client.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
