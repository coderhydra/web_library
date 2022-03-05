package com.example.libraryweb.cont;

import com.example.libraryweb.svc.UserSVC;
import com.example.libraryweb.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/library")
public class LibraryCont {

    @Autowired
    public UserSVC uSvc;

    @GetMapping("")
    public String index() {
        return "redirect:/library/main";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "thymeleaf/pages/privacy";
    }
    @GetMapping("/main")
    public String main() {
        return "thymeleaf/main";
    }
    @GetMapping("/login")
    public String loginG() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginP(
            @RequestParam("uid")String uid,
            @RequestParam("pwd")String pwd,
            Model m
    ) {
        boolean res = uSvc.login(uid, pwd);
        m.addAttribute("uid",uid);
        m.addAttribute("grade",uSvc.getGrade());
        String jstr = String.format("{\"ok\":%b}", res);
        return jstr;
    }
    @PostMapping("/isLogin")
    @ResponseBody
    public boolean isLogin() {
        return uSvc.isLogin();
    }

    //로그아웃기능
    @GetMapping("/logout")
    @ResponseBody
    public boolean logout(SessionStatus status) {
        status.setComplete();
        // 세션에 저장된 모든 데이터를 삭제!
        //바구니 비우기!
        uSvc.logOut();
        System.err.println("user logout");
        return true;
    }

    @GetMapping("/signup")
    public String signUpG() {
        return "join";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signup(UserVO u) {
        boolean res = uSvc.signup(u);
        String jstr = String.format("{\"ok\":%b}", res);
        return jstr;
    }

    @GetMapping("/uid")
    @ResponseBody
    public String uid() {
        return uSvc.getUid();
    }
}
