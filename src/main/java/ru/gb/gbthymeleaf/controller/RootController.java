package ru.gb.gbthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping
    public String getRootPath() {
        return "redirect:/product/all";
    }


    @GetMapping("/auth-confirm")
    public String authConfirm(){
        return "auth-confirm";
    }
}
