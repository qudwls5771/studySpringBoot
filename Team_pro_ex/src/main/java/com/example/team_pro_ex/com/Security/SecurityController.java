package com.example.team_pro_ex.com.Security;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String index(){
        System.out.println("index 요청합니다.");
        return "index";
    }

    @GetMapping("/member")
    public void forMember(){
        System.out.println("Member 요청입니다.");
    }

    @GetMapping("/manager")
    public void forManager(){
        System.out.println("manager 요청입니다.");
    }

    @GetMapping("/admin")
    public void forAdmin(){
        System.out.println("Admin 요청입니다.");
    }
}
