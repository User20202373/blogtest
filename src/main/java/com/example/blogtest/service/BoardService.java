package com.example.blogtest.service;

import com.example.blogtest.errors.Exception404;
import com.example.blogtest.model.board.Board;
import com.example.blogtest.model.board.BoardResponse;
import com.example.blogtest.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 핵심 비즈니스 로직(트랜잭션) : 로직 구현
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardResponse.ListDTO> 게시글목록보기() {
        List<Board> boardList = boardRepository.findAllJoinFetch();
        return boardList.stream()
                .map(BoardResponse.ListDTO::new)
                .collect(Collectors.toList());
    }

    public BoardResponse.DetailDTO 게시글상세조회(Integer id) {
        Board boardEntity = boardRepository.findByIdJoinFetch(id).orElseThrow(() -> {
                    return new Exception404("해당하는 게시글을 찾을 수 없습니다");
                });

        log.info("게시글 조회 완료 - 제목: {}, 작성자: {}",
                boardEntity.getTitle(), boardEntity.getUser().getUsername());
        return new BoardResponse.DetailDTO(boardEntity);

    }
}
