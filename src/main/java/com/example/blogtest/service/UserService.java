package com.example.blogtest.service;

import com.example.blogtest.errors.Exception400;
import com.example.blogtest.model.user.User;
import com.example.blogtest.model.user.UserRequest;
import com.example.blogtest.model.user.UserResponse;
import com.example.blogtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 핵심 비즈니스 로직(트랜잭션) : 로직 구현
@Service // IoC
@RequiredArgsConstructor // DI
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 로그인
    public UserResponse.SessionDTO 로그인(UserRequest.LoginDTO loginDTO) {
        User userEntity = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()).orElseThrow(() -> {
            return new Exception400("사용자명 또는 비밀번호가 올바르지 않습니다");
        });
        return new UserResponse.SessionDTO(userEntity);
    }
}
