package com.ssafy.tourmates.client.board.service.impl;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.repository.BoardRepository;
import com.ssafy.tourmates.client.board.service.BoardService;
import com.ssafy.tourmates.client.board.service.dto.AddBoardDto;
import com.ssafy.tourmates.client.board.service.dto.EditBoardDto;
import com.ssafy.tourmates.client.board.validator.BoardValidator;
import com.ssafy.tourmates.client.member.Active;
import com.ssafy.tourmates.client.member.Member;
import com.ssafy.tourmates.client.member.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ssafy.tourmates.client.member.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardValidator boardValidator;
    private final MemberValidator memberValidator;

    @Override
    public Long registerBoard(String loginId, AddBoardDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .hit(0)
                .active(ACTIVE)
                .member(findMember)
                .build();
        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
    }

    @Override
    public Long editBoard(Long boardId, EditBoardDto dto) {
        Board findBoard = boardValidator.findById(boardId);
        findBoard.changeBoard(dto.getTitle(), dto.getContent());
        return findBoard.getId();
    }
}
