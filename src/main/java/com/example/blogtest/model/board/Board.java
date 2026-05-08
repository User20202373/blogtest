package com.example.blogtest.model.board;


import com.example.blogtest.errors.Exception403;
import com.example.blogtest.model.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;


// DB 테이블과 1:1 매핑 객체
@Entity
@Data
@NoArgsConstructor
@Table(name = "board_tb")
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(Integer id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 편의기능 - 게시글 소유자 확인을 위한 기능 추가
    public boolean isOwner(Integer sessionUserId) {
        if (!this.user.getId().equals(sessionUserId)) {
            throw new Exception403("본인이 작성한 게시글이 아닙니다");
        }
        return true;
    }
}
