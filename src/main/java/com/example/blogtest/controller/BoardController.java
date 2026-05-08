package com.example.blogtest.controller;

import com.example.blogtest.model.board.Board;
import com.example.blogtest.model.board.BoardResponse;
import com.example.blogtest.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// 역할 : 요청 경로 안내 및 모델 전달
@Controller //IoC
@RequiredArgsConstructor //DI
public class BoardController {
    private final BoardService boardService;
    public final HttpSession session;

    //http://localhost:8080/h2-console
    // http://localhost:8080/
    @GetMapping("/")
    public String list(Model model){
        List<BoardResponse.ListDTO> boards = boardService.게시글목록보기();
        model.addAttribute("boards" , boards);
        return "board/list";

    }

    //게시글 상세보기 화면
    @GetMapping("/board/{id}")
    public String detailPage(@PathVariable(name = "id")Integer id , Model model){
        BoardResponse.DetailDTO detailDTO = boardService.게시글상세조회(id);
        model.addAttribute("board",detailDTO);
        return "board/detail";
    }
}
