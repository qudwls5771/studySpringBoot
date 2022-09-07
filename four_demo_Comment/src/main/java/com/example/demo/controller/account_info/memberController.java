package com.example.demo.controller.account_info;

import com.example.demo.Entity.account_info.Member;
import com.example.demo.service.account_info.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

//디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라고 선언
@Controller
@RequestMapping(path="/account")
public class memberController {
    //@Autowired
    //컨트롤러 클래스가 실행되면 memberService를 불러와서 주입 하는 것.
    //주입 당하는 것.
    // @Autowired를 사용해서
    // MemberControlle는 memberService를 주입당하겠다고 선언
    // SpringBoot는 인식 함 : memberController가 실행하려면
    // memberService가 필요함
    //memberController가 필요함
    // 장점1 : memberController 실행되는 시점에서 필요한 객체만 실핼 수 있는 절약
    // 장점2 : 이미 컨테이너에 있는 객체를 활용하여 최대한 인스턴스(객체)를 최소한 사용
    //아래 @Autowired는 필드 주입 방식
    //메소드, 생성자, 필드 (객체의 데이터)
    //필드 주입의 경우에ㅅ는 2개이상 주입할 시 어떤 게 먼저 주입당하는지를 모름
    //주입 당하는 A와 B가 서로 주입당할 경우에는 어떤 게 먼저 생성할지 모르는 문제

    //자바라면~~~!!
    //일반 자바라면, 실행하는 클래스 (main) 안에서 인스턴스를 만들어서 인스턴스 안에 있는
    //메소드를 실행(static : 불러옴)
    //실행되는 클래스(main)이 먼저 존재하고 인스턴스로 후에 생성

    //MemberService라는 객체를 선언
    //필드 주입방식은 @Autowired를 통해 컨테이넌에서 주입당함(할당)
    @Autowired
    private memberService memberservice;

    //MemberService라는 객체를 선언
    //필드 주입방식은 @Autowired를 통해 컨테이넌에서 주입당함(할당)
    //final은 변하지 않게 하는 것. : memberController는 안심하고 memberService를 사용
    //@Autowired
    //private final memberService memberservice;


    //생성자 주입방식은 아래 생성자에 @Autowired를 붙혀서 컨테이너에서 주입 당함
    //memberController 클래스의 생성자를 선언
    //매개변수를 memberService로 받아서 뒤에 있는 필드값 memb에 할당
    //장점 : 객체 생성 시점에서 생성자를 통해서 주입 받기 때문에 순서가 명확해진다.
    //@Autowired
    //protected memberController(memberService memberservice){
    //    this.memberservice = memberservice;
    //}


    //클라이언트 두 분류 : 사용자 관점
    //시스템관리관점(회원관리, 게시판 관리, 컨텐츠 관리)[웹솔루션을 관리하는 오너]
    //getAccountList(전체 외훤 목록 보기) : 웹솔루션에서 웹시스템을 관리하는 관리자 기능
    //public : 전부공개
    //String : 이 메소드가 실행 완료되면 최종적으로 리턴하는 타입(HTML 파일명을 찾기 위해)

    @GetMapping("/getAccountList")
    public String getAccountList(Model model) {
        //model : 컨트롤러에서 작업한 결과물을 HTML에 전달하기 위한 매개체
        //addAttribute : key/value으로 데이터를 저장하는 메서드
        //attributeName(key) : 뒤에 있는 value를 호출하기 위한 문자열(key)
        //memberService.getMemberList() : @Autowired로 선언된 MemberService 클래스를 호출하여
        //getMemberList()메서드 실행
        model.addAttribute("memberList",
                memberservice.getMemberListEncodingByMemberList(
                        memberservice.getMemberList()));
        return "/account/getAccountList";
    }

    //member : 클라이언트
    @GetMapping("/getAccount")
    public String getAccount(Member member, Model model) {
        System.out.println("-----------getAccount----------");
        model.addAttribute("member", memberservice.getMember(member));
        return "/account/getAccount";
    }

    //기준데이터의 무결성 체크를 위한 데이터 전체 조회와 일부 수정작업(sql 특정 컬럼의 값을 모두 gmail.com -> naver.com)

    //*백업 entity
    //회원정보가 일정 수치까지 다다르면(혹은 이벤트가 발생 updateAccountAll라는 메소드를 통해)
    //기존 entity의 테이블의 정보를 모두 백업 entity에 저장
    //+회원정보 1개의 테이블에서 관리하지 않아요 > 1년 지난 회원은 로그인을 안한 장기 휴식회원
    //+1년 미접속 계정은 따로 테이블에 옮겨서 저장 (예전 스타일)
    //날짜별로 1년이 지나면 새로 테이블을 생성해서 회원을 관리합니다.(회원가입한 날짜)
    // > 장점 : 최신회원들을 관리하는 마케팅부서에게 도움
    // > 장점 : DB 백업할 때도 테이블 파편화로 트랜잭션 하는 위험 또는 시간 절약
    // > 단점 : Admin(관리자)는 모든 회원정보를 볼 때 다수의 테이블을 동시에 봐야 하기 때문에
    //         Join을 써서 속도가 느림

    //retrun 타입이 String 이유 : HTMl 파일명을 찾기 위해
    //retrun 타입이 String이유 : HTML 파일명을 찾기 위해
    @GetMapping("/insertAccount")
    public String insertAccountView() {
        return "account/insertAccount";
    }

    //Member 라는 매개변수로 controller에 전달
    //Member(Entity)이고 DTO(Data Transfer Object)
    //어디선가 받거나 만든 데이터를 객체로 만드는 것 : DTO
    @PostMapping("/insertAccount")
    public String insertAccountView(Member member) {
        //클라이언트에서 ID/PW
        memberservice.insertMember(member);
        return "redirect:/account/getAccountList";
    }

    //deleteAccount : 회원정보 삭제

    @PostMapping("/updateAccount")
    public String updateAccount (Member member) {
        memberservice.updateMember(member);
        return "redirect:/account/getAccountList";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(Member member) {
        System.out.println("-------delete-------");
        memberservice.deleteMember(member);
        return "redirect:/account/getAccountList";
    }
    //selectAccount HTML을 보여주는 것.
    @GetMapping("/selectAccount")
    public String selectAccount() {
        return "account/selectAccount";
    }

    @PostMapping("/selectAccount")
    public String resultAccount(Member member, Model model) {
        model.addAttribute("memberList",
                memberservice.getMemberWhereIdOrEmail(member.getEmail(), member.getId()));
        return "account/resultAccount";
    }

    @GetMapping("/selectEmail")
    public String selectEmail(){
        return "account/selectEmail";
    }

    @RequestMapping(value="/selectEmail", method = RequestMethod.POST)
    public String resultEmail(@RequestParam("email") String email,
                              @RequestParam("id") String id,
                              Member member, Model model){
        model.addAttribute("member", memberservice.findEmail(member.getEmail()));
        model.addAttribute("id", id);
        model.addAttribute("email", email);
        return "account/resultEmail";
    }


}
