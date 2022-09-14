package com.example.security.Login;

import com.example.security.Entity.Member;
import com.example.security.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("member")
@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/loginSuccess")
    public void loginSuccess(){

    }

    @PostMapping("/login")
    public String login(Member member, Model model){
        Member findMember = memberService.getMember(member);
        if(findMember != null && findMember.getPassword().equals(member.getPassword())){
            model.addAttribute("member", findMember);
            return "redirect:/loginSuccess";
        }else{
            return "redirect:/login";
        }
    }






    @GetMapping("/insert")
    public String insert(){
        return "/insert";
    }

    @PostMapping("/insert")
    public String insert(Member member){
        memberService.insertMember(member);

        return "redirect:/login";
    }







}
