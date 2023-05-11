package com.ssafy.tourmates.client.board.validator;

import com.ssafy.tourmates.client.board.Board;
import com.ssafy.tourmates.client.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class BoardValidator {

    private final BoardRepository boardRepository;

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
