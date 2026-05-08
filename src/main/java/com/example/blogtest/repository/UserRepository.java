package com.example.blogtest.repository;

import com.example.blogtest.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// Db 실제 접근(SQL실행)

public interface UserRepository extends JpaRepository<User, Integer> {

    // 사용자 명으로 사용자 조회 (중복 체크 확인 용)
    @Query("""
                SELECT u FROM User u WHERE u.username = :username
            """)
    Optional<User> findByUsername(@Param("username") String username);

    // 사용자 명과 비밀번호로 사용자 조회
    Optional<User> findByUsernameAndPassword(@Param("username") String username,
                                             @Param("password") String password);
}
