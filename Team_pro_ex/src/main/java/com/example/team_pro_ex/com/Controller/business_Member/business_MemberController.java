package com.example.team_pro_ex.com.Controller.business_Member;


import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import com.example.team_pro_ex.com.Service.business_Member.business_MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@RequestMapping(path = "/businessMember")
@SessionAttributes("business_member")
public class business_MemberController {

    private final business_MemberService business_Memberservice;


    @Autowired
    protected business_MemberController(business_MemberService business_memberService){
        this.business_Memberservice = business_memberService;
    }

    @GetMapping("/bm_Join/bm_Join")
    public String business_Insert(businessMember business_member, Model model){
        System.out.println("get mapping account !!");
        System.out.println("get방식으로 인한 Join페이지 = 우리가 처음 join페이지를 들어갈 떄는 null값이 뜰 수 밖에없다.");
        System.out.println("왜냐!!?!? 값이 없으니까!");
        business_member.getId();
        businessMember business_member_1 = new businessMember(
                    business_member.getBusiness_Number_Seq(), // 사업자 회원등록 번호 : PK
                    business_member.getId(), // 아이디
                    business_member.getPassword(), // 비밀번호
                    business_member.getBusinessNumber(), // 사업자 등록번호
                    business_member.getPhoneNumber(), // 사업자 핸드폰 번호
                    business_member.getJoinM(), //가입상태
                    business_member.getRole()
        );
        model.addAttribute("business_member", business_member_1);
        return "/businessMember/bm_Join/bm_Join";
    }

    @PostMapping("/bm_Join/bm_Join")
    public String business_Insert(businessMember business_member, Errors errors, Model model){
        System.out.printf("----사업자_가입-----");
        System.out.println("---check---");
        System.out.println("---PostMapping 실제로 여기서 값이 들어감---");
        System.out.println("아이디 : " + business_member.getId());
        System.out.println("비밀번호 : " + business_member.getPassword());
        System.out.println("사업자 등록번호 : " + business_member.getBusinessNumber());
        System.out.println("사업자 핸드폰 번호 : " + business_member.getPhoneNumber());
        System.out.println("가입생태 : " + business_member.getJoinM());
        System.out.println("권한 : " + business_member.getRole());
        //@Valid : 클라이언트 입력 데이터가 dto클래스로 캡슐화되어 넘어올 때, 유효성을 체크하라는 어노테이션
        //business_Member 에서 작성한 어노테이션을 기준으로 유효성 체크
        //여기서 Errors객체는 business_Member 필드 유효성 검사 오류에 대한 정보를 저장하고 노출한다.
        //errors.hasErrors() : 유효성 검사에 실패한 필드가 있는지 확인
        if(errors.hasErrors()){
            //회원가입 실패 시, 입력 데이터를 유지
            model.addAttribute("business_member" ,business_member);
            //회원가입 실패 시, 회원가입 페이지에서 입력했던 정보들을 그대로 유지하기 위해 입력받았던 데이터를 그대로 할당합니다.
            //business_Insert(business_Member business_member) 함수에 파라미터를 정의해준 이유입니다.
            //Validation 관점에서는 필요없는 부분이지만, UX 측면에서 구현해주는 것이 좋다.
            //물론, thymeleaf에서도 코드가 들어가야 한다.
            //유효성을 통과 못한 필드와 메세지를 핸들링
            Map<String, String> business_Member_Availability = business_Memberservice.business_Member_Availability(errors);
            for(String key : business_Member_Availability.keySet()){
                model.addAttribute(key, business_Member_Availability.get(key));
            }
            return "/businessMember/bm_Join/bm_Join";
        }
        business_Memberservice.insertBusiness_Member(business_member);
        return "redirect:/businessMember/LoginBm";
    }

    @GetMapping("/bm_Update/bm_Update")
    public String myBMpage(businessMember business_member, Model model){
        System.out.println("사업자 회원 수정 목록만 보여줌");
        businessMember business_member_1 = new businessMember(
                business_member.getBusiness_Number_Seq(),
                business_member.getId(),
                business_member.getPassword(),
                business_member.getBusinessNumber(),
                business_member.getPhoneNumber(),
                business_member.getJoinM(),
                business_member.getRole()
        );
        model.addAttribute("business_member", business_member_1);

        return "/businessMember/bm_Update/bm_Update";
    }

    @PostMapping("/bm_Update/bm_Update")
    public String update_business_Member(businessMember business_member, Errors errors, Model model){
        System.out.printf("---------업데이트---------");
        System.out.println("사업자의 수정은 실질적으로 여기서 이루어진다.");
        System.out.println("check");
        System.out.println("회원 번호 : " + business_member.getBusiness_Number_Seq());
        System.out.println("아이디 : " + business_member.getId());
        System.out.println("비밀번호 : " + business_member.getPassword());
        System.out.println("사업자 등록번호 : " + business_member.getBusinessNumber());
        System.out.println("사업자 핸드폰 번호 : " + business_member.getPhoneNumber());
        System.out.println("가입생태 : " + business_member.getJoinM());
        System.out.println("권한 : " + business_member.getRole());

        if(errors.hasErrors()){
            model.addAttribute("business_member", business_member);
            System.out.println("회원 아이디 : " + business_member.getId());

            Map<String, String> business_Member_Availability = business_Memberservice.business_Member_Availability(errors);
            for(String key : business_Member_Availability.keySet()){
                model.addAttribute(key, business_Member_Availability.get(key));
            }
            return "redirect:/businessMember/bm_Update/bm_Update";
        }
        business_Memberservice.updateBusiness_Member(business_member);
        return "redirect:/businessMember/LoginBm";
    }


    @PostMapping("/bmDelete/upDelete")
    public String deleteUpdateBM(businessMember business_member){
        System.out.println("------사업자 회원 삭제------");
        business_Memberservice.deleteUpdateBusiness_Member(business_member);
        return "redirect:/businessMember/LoginBm";
    }

    @GetMapping("/LoginBm")
    public void loginView(){

    }

    @PostMapping("/LoginBm")
    public String Login(businessMember business_member, Model model){
        businessMember findBusiness = business_Memberservice.getMember(business_member);
        if(findBusiness != null
                && findBusiness.getPassword().equals(business_member.getPassword())
                && findBusiness.getId().equals(business_member.getId())) {
            model.addAttribute("business_member", findBusiness);
            System.out.println("로그인 성공!!");
            return "redirect:/businessMember/bmLoginPage";
        }else{
            System.out.println("아이디, 비밀번호 불일치 다시 입력해주세요");
            return "redirect:/businessMember/Login";
        }
    }

    @GetMapping("bmLoginPage")
    public void LoginPage(){

    }




}
