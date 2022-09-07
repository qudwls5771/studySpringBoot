package com.example.lecture_spring_2_crudproject;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.repository.account.MemberRepository;
import com.example.lecture_spring_2_crudproject.service.openAPI.PublicAPI;
import com.example.lecture_spring_2_crudproject.service.textTransfer.SeleniumExample;
import com.example.lecture_spring_2_crudproject.service.textTransfer.TextTransfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LectureSpring2CrudProjectApplicationTests {




    @Autowired
    TextTransfer textTransfer;

    @Autowired
    PublicAPI publicAPI;

    @Test
    void apiTest() {
        publicAPI.testAPI();
    }

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("저장, 데이터가 잘 들어갔는지 확인")
    void contextSave() {
        //Setter로 엔티티를 생성하고 repositoy가 정상 작동하는지 확인
        Member member = new Member();
        //클라이언트에서 controller에 데이터를 전달하는 내용을 setter를 통해 대체
        member.setId("humanClass4");
        member.setPassword("12341234@");
        member.setEmail("class4@123.com");
        //memberRepository의 save메서드가 정상 동작하는지 확인
        memberRepository.save(member);
    }

    @Test
    void textTest() throws Exception {
        textTransfer.transferText3Word("abcdefg@gmil.com");
    }
    @Autowired
    SeleniumExample selenium;
    @Test
    void Scraping(){
        selenium.scraping();
    }

}
