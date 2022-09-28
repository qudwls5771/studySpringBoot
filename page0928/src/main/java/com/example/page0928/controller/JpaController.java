package com.example.page0928.controller;


import com.example.page0928.Entity.Member;
import com.example.page0928.dto.memberDTO;
import com.example.page0928.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JpaController {

    @Autowired
    private MemberRepository memberRepository;



    @RequestMapping(value = "/jpa/memberWriteForm", method = RequestMethod.GET)
    public String memberWrite(@RequestParam(value = "num", required = false) Integer num, Model model){

        if(num != null){
            //기존회원 수정
        }else{
            //신규
            System.out.println("신규 등록....");
            model.addAttribute("memberDTO", new memberDTO());
            model.addAttribute("formTitle", "Registration");
        }



        return "/jpa/memberWriteForm";
    }

    @RequestMapping(value = "/jpa/memberWriteForm", method = RequestMethod.POST)
    public String memberWrite(
            memberDTO memberDTO, Model model
    ){

        try {
            System.out.println(memberDTO);

            //memberDTO -> entity
            Member save = memberDTO.toEntity();

            //Repository를 이용한 DB작업
            Member saved = memberRepository.save(save);
            System.out.println("제대로 저장이 됐는지 !?!?!  : " + saved);
        }catch (Exception e){
            e.printStackTrace();
        }


        return "redirect:/";
    }

    // jpa/memberList
    @RequestMapping(value = "/jpa/memberList", method = RequestMethod.GET)
    public String memberList(
            Model model
    ){
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);


        return "/jpa/memberList";
    }




}
