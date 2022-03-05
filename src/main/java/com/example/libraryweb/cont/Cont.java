package com.example.libraryweb.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Cont {

    @GetMapping("/")
    public String index(){
        return "redirect:/library/main";
    }
    @GetMapping("/jsp")
    public String jsp(){
        return "jsp";
    }
    @GetMapping("/thymeleaf")
    public String thymeleaf(){
        return "thymeleaf/thymeleaf";
    }
}
