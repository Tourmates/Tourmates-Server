package com.ssafy.tourmates.client.board.service;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;
import static com.ssafy.tourmates.client.member.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.BoardComment;
import com.ssafy.tourmates.client.board.repository.BoardCommentRepository;
import com.ssafy.tourmates.client.board.repository.BoardRepository;
import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardCommentDto;
import com.ssafy.tourmates.client.hotplace.HotPlace;
import com.ssafy.tourmates.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.repository.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardCommentServiceTest {

  @Autowired
  private BoardCommentService boardCommentService;

  @Autowired
  private BoardCommentRepository boardCommentRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BoardRepository boardRepository;

  private Member savedMember;
  private Board savedBoard;

  @BeforeEach
  void beforeEach() {
    Member member = Member.builder()
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

    Board board = Board.builder()
        .content("내용1")
        .title("제목1")
        .member(savedMember)
        .active(ACTIVE)
        .build();

    savedBoard = boardRepository.save(board);
  }

  @Test
  @DisplayName("게시물 댓글 등록")
  void registerBoardComment(){
    //given
    AddBoardCommentDto addBoardCommentDto = AddBoardCommentDto.builder().
        content("내용1")
        .build();

    //when
    Long boardCommentId = boardCommentService.registerBoardComment(savedMember.getLoginId(),
        savedBoard.getId(), addBoardCommentDto);

    //then
    Optional<BoardComment> findBoardComment = boardCommentRepository.findById(boardCommentId);
    assertThat(findBoardComment).isPresent();
  }

  @Test
  @DisplayName("게시물 댓글 수정")
  void editBoardComment(){
    //given
    EditBoardCommentDto editBoardCommentDto = EditBoardCommentDto.builder()
          .content("수정된 내용")
          .build();

    BoardComment savedBoardComment = BoardComment.builder()
        .content("원본 내용")
        .build();

    BoardComment originalBoardComment = boardCommentRepository.save(savedBoardComment);

    //when
    Long boardCommentId = boardCommentService.editBoardComment(savedMember.getId(),
        originalBoardComment.getId(), editBoardCommentDto);

    //then
    Optional<BoardComment> editBoardComment = boardCommentRepository.findById(boardCommentId);

    assertThat(editBoardComment).isPresent();
    assertThat(editBoardComment.get().getContent()).isEqualTo(editBoardCommentDto.getContent());
  }
}
