package com.ssafy.tourmates.client.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.tourmates.client.member.Active.*;
import static org.assertj.core.api.Assertions.*;

class BoardTest {

    @Test
    @DisplayName("게시물 변경")
    void changeBoard() {
        //given
        Board board = Board.builder()
                .title("게시물 제목")
                .content("게시물 내용입니다.")
                .build();

        //when
        board.changeBoard("변경된 게시물 제목", "변경된 게시물 내용입니다.");

        //then
        assertThat(board.getTitle()).isEqualTo("변경된 게시물 제목");
    }

    @Test
    @DisplayName("게시물 삭제")
    void deActive() {
        //given
        Board board = Board.builder()
                .active(ACTIVE)
                .build();

        //when
        board.deActive();

        //then
        assertThat(board.getActive()).isEqualTo(DEACTIVE);
    }

    @Test
    @DisplayName("게시물 조회수 증가")
    void increaseHit() {
        //given
        Board board = Board.builder()
                .build();

        //when
        board.increaseHit();

        //then
        assertThat(board.getHit()).isEqualTo(1);
    }
}