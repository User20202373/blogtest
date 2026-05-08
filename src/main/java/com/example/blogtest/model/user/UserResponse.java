package com.example.blogtest.model.user;

import lombok.Data;

// 응답 DTO
public class UserResponse {

    @Data
    public static class joinDTO {
        private Integer id;
        private String username;
        private String email;

        public joinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }

    @Data
    public static class SessionDTO extends User{


        public SessionDTO(User user) {
            super.setId(user.getId());
            super.setUsername(user.getUsername());
            super.setEmail(user.getEmail());
        }
    }
}
