package com.example.security.Security;

import org.springframework.web.bind.annotation.GetMapping;

public class SecurityController {
    @GetMapping("/")
    public String index(){
        System.out.println("index 요청합니다.");
        return "index";
    }
    @GetMapping("/login")
    public void login(){

    }

//    @GetMapping("/login")
//    public void memberLogin(){
//        System.out.println("MemberLogin");
//    }

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
