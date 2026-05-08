package com.example.blogtest.controller;

import com.example.blogtest.model.user.User;
import com.example.blogtest.model.user.UserRequest;
import com.example.blogtest.model.user.UserResponse;
import com.example.blogtest.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

// 역할 : 요청 경로 안내 및 모델 전달
@Controller //IoC
@RequiredArgsConstructor // DI
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    // 로그인 화면 요청
    @GetMapping("/login-form")
    public String loginFormPage() {
        return "user/login-form";
    }
    // 로그인
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO,HttpSession session) {
        loginDTO.validate();
        UserResponse.SessionDTO sessionDTO = userService.로그인(loginDTO);

        session.setAttribute("sessionUser", sessionDTO);
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";

    }

}
