package com.springboot.catchmind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        return "/main";
    }

    @GetMapping("/chatRoomList")
    public String chatRoomList() {
        return "chatPageList/chatRoomList";
    }

}
