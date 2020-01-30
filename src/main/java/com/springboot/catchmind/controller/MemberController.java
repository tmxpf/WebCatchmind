package com.springboot.catchmind.controller;

import com.springboot.catchmind.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/user/signup")
    public String signup() {
        return "/loginAndSignup/signup";
    }

}
