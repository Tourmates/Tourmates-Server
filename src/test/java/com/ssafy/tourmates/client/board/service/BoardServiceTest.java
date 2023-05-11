package com.ssafy.tourmates.client.board.service;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.repository.BoardRepository;
import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardDto;
import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.tourmates.client.member.Active.*;
import static com.ssafy.tourmates.client.member.Gender.MALE;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;
    private Board savedBoard;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .id(1L)
                .loginId("ssafy1234")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .email("ssafy@ssafy.com")
                .tel("010-1234-1234")
                .birth("2000.01.01")
                .gender(MALE)
                .nickname("ssafy")
                .build();
        savedMember = memberRepository.save(member);
    }

    @Test
    @DisplayName("게시물 작성")
    void registerBoard() {
        //given
        AddBoardDto dto = AddBoardDto.builder()
                .title("새로운 게시물 제목")
                .content("새로운 게시물 내용입니다.")
                .build();

        //when
        Long boardId = boardService.registerBoard(savedMember.getLoginId(), dto);

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
    }

    @Test
    @DisplayName("게시판 수정")
    void editBoard() {
        //given
        createBoard();
        EditBoardDto dto = EditBoardDto.builder()
                .title("수정된 게시물 제목")
                .content("수정된 게시물 내용입니다.")
                .build();

        //when
        Long boardId = boardService.editBoard(savedBoard.getId(), dto);

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getTitle()).isEqualTo(dto.getTitle());
    }

    @Test
    @DisplayName("게시물 삭제")
    void removeBoard() {
        //given
        createBoard();

        //when
        Long boardId = boardService.removeBoard(savedBoard.getId());

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getActive()).isEqualTo(DEACTIVE);
    }

    private void createBoard() {
        Board board = Board.builder()
                .title("게시물 제목")
                .content("게시물 내용입니다.")
                .hit(0)
                .active(ACTIVE)
                .member(savedMember)
                .build();
        savedBoard = boardRepository.save(board);
    }
}