package com.example.blogtest.model.board;

import lombok.Data;

/**
 * 게시글 응답 DTO
 * Open Session In View 가 false일 때
 * 트랜잭션이 종료되는 시점에 LAZY로딩이 불가능하다
 * Service 단에서 필요한 데이터를 모두 조회 또는 일부로 호출(트리거)해서 응답 DTO 변환해서 반환
 * 엔티티를 직접 반환하지 않고 (controller,View) 서비스 단에서 DTO내려줄 예정 (결합도 감소)
 */
public class BoardResponse {

    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String username;
        private String content;

        public ListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            if (board.getUser() != null) {
                this.username = board.getUser().getUsername();
            }
            this.content = board.getContent();

        }
    }

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private String username;
        private Integer userId; // user PK
        private boolean isOwner;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            if (board.getUser() != null) {
                this.username = board.getUser().getUsername();
                this.userId = board.getUser().getId();
            }

        }
    }
}
