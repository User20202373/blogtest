package com.example.blogtest.repository;

import com.example.blogtest.model.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// Db 실제 접근(SQL실행)
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //단일조회
    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.id = :id")
    Optional<Board> findByIdJoinFetch(@Param("id") Integer id);

    // 전체 조회
    @Query("SELECT b FROM Board b JOIN FETCH b.user")
    List<Board> findAllJoinFetch();
}
