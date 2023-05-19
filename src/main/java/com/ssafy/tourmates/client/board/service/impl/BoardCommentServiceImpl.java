package com.ssafy.tourmates.client.board.service.impl;

import com.ssafy.tourmates.client.api.dto.board.response.BoardCommentResponse;
import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.BoardComment;
import com.ssafy.tourmates.client.board.repository.BoardCommentRepository;
import com.ssafy.tourmates.client.board.service.BoardCommentService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardCommentDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardCommentDto;
import com.ssafy.tourmates.client.board.validator.BoardCommentValidator;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

    private final MemberValidator memberValidator;
    private final BoardCommentValidator boardCommentValidator;
    private final BoardCommentRepository boardCommentRepository;

    @Override
    public Long registerBoardComment(String loginId, Long boardId, AddBoardCommentDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);

        BoardComment boardComment = BoardComment.builder()
                .content(dto.getContent())
                .member(findMember)
                .board(Board.builder().id(boardId).build())
                .build();

        BoardComment savedBoardComment = boardCommentRepository.save(boardComment);
        return savedBoardComment.getId();
    }

    @Override
    public Long editBoardComment(Long boardCommentId, EditBoardCommentDto dto) {
        BoardComment findBoardComment = boardCommentValidator.findById(boardCommentId);
        findBoardComment.changeComment(dto.getContent());
        return findBoardComment.getId();
    }

    @Override
    public Long removeBoardComment(Long boardCommentId) {
        boardCommentRepository.deleteById(boardCommentId);
        return boardCommentId;
    }

    @Override
    public List<BoardCommentResponse> searchAll(Long boardId) {
        List<BoardComment> boardCommentList =  boardCommentValidator.findByBoardId(boardId);

        List<BoardCommentResponse> boardCommentResponseList = new ArrayList<>();

        for(int i = 0; i < boardCommentList.size(); i++){
            BoardComment comment = boardCommentList.get(i);
            boardCommentResponseList.add(new BoardCommentResponse(comment.getContent()));
        }

        return boardCommentResponseList;
    }
}
