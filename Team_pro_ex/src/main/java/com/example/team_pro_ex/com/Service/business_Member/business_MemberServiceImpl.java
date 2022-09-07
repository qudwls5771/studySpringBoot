package com.example.team_pro_ex.com.Service.business_Member;

import com.example.team_pro_ex.com.Entity.business_Member.business_Member;
import com.example.team_pro_ex.com.persistence.business_Member.business_MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class business_MemberServiceImpl implements business_MemberService{

    @Autowired
    private business_MemberRepository business_memberRepo;

    @Autowired
    protected business_MemberServiceImpl(business_MemberRepository business_memberRepo){
        this.business_memberRepo = business_memberRepo;
    }

    @Override
    public List<business_Member> getbusiness_MemberList(business_Member business_member) {
        System.out.println("--------사업자 회원 목록---------");
        return (List<business_Member>) business_memberRepo.findAll();
    }



    @Override
    public void updateBusiness_Member(business_Member business_member) {
        business_Member findBusiness_member = new business_Member();
        System.out.println("--------회원정보 수정---------");
        System.out.println(findBusiness_member.getBusiness_Number());
        System.out.println(findBusiness_member.getId());
        System.out.println(findBusiness_member.getPassword());
        System.out.println(findBusiness_member.getPhone_number());
        System.out.println(findBusiness_member.getStore_number());
        System.out.println(findBusiness_member.getStore_Name());
        System.out.println(findBusiness_member.getAddress());
        System.out.println(findBusiness_member.getJoin_M());
        System.out.println("--------회원정보 수정---------");
        business_memberRepo.save(findBusiness_member);
    }

    @Override
    public void deleteUpdateBusiness_Member(business_Member business_member) {
        //business_memberRepo.(business_member);
        System.out.println("--------사업자 회원 삭제(수정  = Null처리)---------");
    }

    @Override
    public Map<String, String> business_Member_Availability(Errors errors) {
        //유효성 검사에 실패한 필드들은 Map 자료구조를 통해 키값과 에러 메시지를 응답한다.
        //Key : valid_{dto 필드명}
        //Message : dto에서 작성한 message 값
        //유효성 검사에 실패한 필드 목록을 받아 미리 정의된 메시지를 가져와 Map에 넣어준다.
        Map<String, String> availability_ID = new HashMap<>();
        //유효성 검사에 실패한 필드 목록을 받음
        // errors.getFieldErrors() : 유효성 검사에 실패한 필드 목록을 가져옴
        for(FieldError error : errors.getFieldErrors()){
            //유효성 검사에 실패한 필드명을 가져온다. : error.getField() / 키 : valid_%s = > vaild_id 이런 식으로 html에 표시
            String bsusiness_availability_ID = String.format("valid_%s", error.getField());
            //error.getDefaultMessage() : 유효성 검사에 실패한 필드에 정의된 메시지를 가져옵니다.
            availability_ID.put(bsusiness_availability_ID, error.getDefaultMessage());
        }

        return availability_ID;
    }

    @Override
    public void insertBusiness_Member(business_Member business_member) {
        System.out.println("--------사업자 회원 가입---------");
        business_memberRepo.save(business_member);
    }
}
