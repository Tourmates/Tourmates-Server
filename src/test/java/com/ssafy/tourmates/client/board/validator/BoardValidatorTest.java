package com.ssafy.tourmates.client.board.validator;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.repository.BoardRepository;
import com.ssafy.tourmates.client.member.Active;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.tourmates.client.member.Active.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardValidatorTest {

    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardRepository boardRepository;

    private Board savedBoard;

    @BeforeEach
    void beforeEach() {
        Board board = Board.builder()
                .title("게시판 제목")
                .content("게시판 내용입니다.")
                .hit(0)
                .active(ACTIVE)
                .build();
        savedBoard = boardRepository.save(board);
    }

    @Test
    @DisplayName("게시물 PK 조회")
    void findById() {
        //given

        //when
        Board findBoard = boardValidator.findById(savedBoard.getId());

        //then
        assertThat(findBoard.getId()).isEqualTo(savedBoard.getId());
    }
}