package com.ssafy.tourmates.client.board.service.impl;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.BoardComment;
import com.ssafy.tourmates.client.board.repository.BoardCommentRepository;
import com.ssafy.tourmates.client.board.service.BoardCommentService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardCommentDto;
import com.ssafy.tourmates.client.board.validator.BoardCommentValidator;
import com.ssafy.tourmates.client.board.validator.BoardValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

    private final MemberValidator memberValidator;
    private final BoardValidator boardValidator;
    private final BoardCommentValidator boardCommentValidator;
    private final BoardCommentRepository boardCommentRepository;

    @Override
    public Long registerBoardComment(String loginId, Long boardId, AddBoardCommentDto dto) {

        Member findMember = memberValidator.findByLoginId(loginId);
        Board board = boardValidator.findById(boardId);

        BoardComment boardComment = BoardComment.builder()
                .content(dto.getContent())
                .member(findMember)
                .board(board)
                .build();

        BoardComment savedBoardComment = boardCommentRepository.save(boardComment);

        return savedBoardComment.getId();
    }

    @Override
    public Long editBoardComment(Long boardId, Long boardCommentId, EditBoardCommentDto dto) {

        BoardComment findBoardComment = boardCommentValidator.findById(boardCommentId);
        findBoardComment.changeBoardComment(dto.getContent());
        return findBoardComment.getId();
    }

    @Override
    public void removeBoardComment(Long boardCommentId) {
        boardCommentRepository.deleteById(boardCommentId);
    }
}
