package com.example.team_five.Controller.Member;


import com.example.team_five.Entity.Member.Member;
import com.example.team_five.Service.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(path="/Member")
public class memberController {

    private final MemberService memberService;

    @Autowired
    protected memberController(MemberService memberService){
        this.memberService = memberService;
    }
    //회원가입
    @GetMapping("회원가입 페이지")
    public String insertMember(Member member, Model model){
        System.out.println("!!!------GetMapping------!!!");
        System.out.println("get방식으로 인해 실제적으로 데이터가 들어가는 곳은 post임");
        Member insertMember = new Member(
                member.getId(),
                member.getPassword(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAddress(),
                member.getGender(),
                member.getYear()
        );
        model.addAttribute("member", insertMember);
        return "회원 가입 페이지";
    }

    //회원가입
    @PostMapping("이또한 마찬가지") //유효성 검사를 사용하기 위해서 @Valid 필요, 꼭일까? @Valid 뒤에 Entity를 입력할 것.
    public String insertMember(@Valid Member member, Errors errors, Model model){
        System.out.println("---------Check----------");
        System.out.println("Post방식으로 실질적으로 데이터가 저장되는 곳");
        System.out.println("아이디 :"+member.getId());
        System.out.println("비밀번호 :"+member.getPassword());
        System.out.println("이름 :"+member.getName());
        System.out.println("이메일 :"+member.getEmail());
        System.out.println("핸드폰 :"+member.getPhone());
        System.out.println("주소 :"+member.getAddress());
        System.out.println("성별 : "+member.getGender());
        System.out.println("생년월일 : " + member.getYear());
        //@Valid : 클라이언트 입력 데이터가 dto클래스로 캡슐화되어 넘어올 때, 유효성을 체크하라는 어노테이션
        //Member에서 작성한 어노테이션을 기준으로 유효성 체크
        //여기서 Errors객체는 Member의 필드 유효성 검사 오류에 대한 정보를 저장하고 노출한다.
        //errors.hasErrors() : 유효성 검사에 실패한 필드가 있는지 확인

        if(errors.hasErrors()){
            // model을 적어준 이유는 회원가입 실패 시, 입력 데이터를 유지해주기 위해서이다.
            // insertMember(Member member) 함수에 파라미터를 정의해준 이유
            // validation 관점에서는 필요없는 부분이다. 근데 ux 측면에서는 좋을 듯?
            // 물론, thymeleaf에서도 코드가 들어가야 됨
            model.addAttribute("member", member);
            System.out.println(member.getId());


            //유효성 검사를 통과를 못한 필드와 메세지를 출력해주는 것!
            Map<String, String> member_Availability = memberService.member_Availability(errors);
            for(String key : member_Availability.keySet()){
                model.addAttribute(key, member_Availability.get(key));
            }
            //유효성을 통과하지 못할 경우 회원가입 페이지를 리턴
            return "유효성 겸사를 통과를 못한 회원은 회원가입 페이지로 리턴;";
        }
        //유효성 검사를 통과하고 난 이후의 페이지로 이동
        memberService.insertMember(member);
        return "유효성 검사를 통과한 후 페이지를 입렵";

    }
    //회원 수정
    @GetMapping("회원수정 페이지")
    public String updateMember(Member member, Model model){
        System.out.println("!!!------GetMapping------!!!");
        System.out.println("get방식으로 인해 실제적으로 데이터가 들어가는 곳은 post임");
        Member insertMember = new Member(
                member.getId(),
                member.getPassword(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAddress(),
                member.getGender(),
                member.getYear()
        );
        model.addAttribute("member", insertMember);
        return "회원 수정 페이지";
    }

    //회원수정
    @PostMapping("회원수정 페이지")
    public String updateMember(Member member, Model model, Errors errors){
        System.out.println("---------Check----------");
        System.out.println("Post방식으로 실질적으로 데이터가 저장되는 곳");
        System.out.println("아이디 :"+member.getId());
        System.out.println("비밀번호 :"+member.getPassword());
        System.out.println("이름 :"+member.getName());
        System.out.println("이메일 :"+member.getEmail());
        System.out.println("핸드폰 :"+member.getPhone());
        System.out.println("주소 :"+member.getAddress());
        System.out.println("성별 : "+member.getGender());
        System.out.println("생년월일 : " + member.getYear());
        //@Valid : 클라이언트 입력 데이터가 dto클래스로 캡슐화되어 넘어올 때, 유효성을 체크하라는 어노테이션
        //Member에서 작성한 어노테이션을 기준으로 유효성 체크
        //여기서 Errors객체는 Member의 필드 유효성 검사 오류에 대한 정보를 저장하고 노출한다.
        //errors.hasErrors() : 유효성 검사에 실패한 필드가 있는지 확인

        if(errors.hasErrors()){
            // model을 적어준 이유는 회원가입 실패 시, 입력 데이터를 유지해주기 위해서이다.
            // insertMember(Member member) 함수에 파라미터를 정의해준 이유
            // validation 관점에서는 필요없는 부분이다. 근데 ux 측면에서는 좋을 듯?
            // 물론, thymeleaf에서도 코드가 들어가야 됨
            model.addAttribute("member", member);
            System.out.println(member.getId());


            //유효성 검사를 통과를 못한 필드와 메세지를 출력해주는 것!
            Map<String, String> member_Availability = memberService.member_Availability(errors);
            for(String key : member_Availability.keySet()){
                model.addAttribute(key, member_Availability.get(key));
            }
            //유효성을 통과하지 못할 경우 회원가입 페이지를 리턴
            return "유효성 겸사를 통과를 못한 회원은 회원수정 페이지로 리턴;";
        }
        //유효성 검사를 통과하고 난 이후의 페이지로 이동
        memberService.updateMember(member);
        return "유효성 검사를 통과한 후 페이지를 입렵";
    }

    //로그인
    @GetMapping("로그인 페이지")
    public void loginView(){

    }
    //로그인
    @PostMapping("로그인 페이지")
    public String login(Member member, Model model){
        Member findMember = memberService.getMember(member);
        //아이디, 비번 일치해야지 로그인 가능
        //회원 로그인을 할 경우 아이디와 비밀번호가 일치하지 않으면 로그인 페이지로 되돌아 온다.
        if(findMember != null
            && findMember.getId().equals(member.getId())
            && findMember.getPassword().equals(member.getPassword())){
            model.addAttribute("member", findMember);
            System.out.println("로그인 성공!!!");
            return "로그인 성공 후 페이지";
        }else{
            System.out.println("아이디, 비밀번호를 다시 입력해주세요!");
            return "로그인 페이지";
        }
    }
    @GetMapping("로그아웃")
    public String logout(SessionStatus status){
        status.setComplete();
        System.out.println("로그아웃");
        return "로그아웃 후 페이지";
    }

    @PostMapping("회원 탈퇴")
    public String deleteMember(Member member){
        System.out.println("---------회원탈퇴---------");
        memberService.deleteMember(member);
        return "탈퇴하고 난 후 페이지";
    }





}
