package com.example.team_pro_ex.com.Service.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import com.example.team_pro_ex.com.persistence.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class memberServiceImpl implements memberService{

    //persistence.account_info => MemberRepository에 있는 CrudRepository<Member, Long> 사용
    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    protected memberServiceImpl(MemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }

    //회원 전체조회
    @Override
    public List<Member> getMemberList(Member member) {
        System.out.println("--------회원목록---------");
        return (List<Member>) memberRepo.findAll();
    }


    //회원정보 업데이트
    @Override
    public void updateMember(Member member) {
        Member findMember = memberRepo.findById(member.getId()).get();
        System.out.println("--------회원정보 수정---------");
        System.out.println(member.getId());
        System.out.println(member.getPassword());
        System.out.println(member.getName());
        System.out.println(member.getPhoneNumber());
        System.out.println(member.getAddress());
        System.out.println(member.getPetT());
        System.out.println(member.getPetS());
        System.out.println(member.getPetD());
        System.out.println(member.getPetW());
        System.out.println(member.getJoinM());
        System.out.println("--------회원정보 수정---------");
        memberRepo.save(findMember);
    }

    //고객의 데이터 정보는 돈이다!? 라는게 있어서 고객의 ID, 이름, 가입상태(Y=>N으로 변경)을 제외한 모든
    //데이터를 Null값으로 변경
    //날짜 테이블에 있는 가입날짜, 탈퇴날짜 수정은 차후에 하기로 한다.
    @Override
    public void deleteUpdateMember(Member member) {
        System.out.println("--------회원탈퇴---------");
        memberRepo.updateDelete(member.getId());
    }


    //회원가입 시, 유효성 체크
    @Override
    public Map<String, String> member_Availability(Errors errors) {
        //유효성 검사에 실패한 필드들은 Map 자료구조를 통해 키값과 에러 메시지를 응답한다.
        //Key : valid_{dto 필드명}
        //Message : dto에서 작성한 message 값
        //유효성 검사에 실패한 필드 목록을 받아 미리 정의된 메시지를 가져와 Map에 넣어준다.
        Map<String, String> availability_ID = new HashMap<>();
        /* 유효성 검사에 실패한 필드 목록을 받음 */
        // errors.getFieldErrors() : 유효성 검사에 실패한 필드 목록을 가져옴
        for(FieldError error : errors.getFieldErrors()){
            //유효성 검사에 실패한 필드명을 가져옵니다. : error.getField() / 키 : "members_%s"  = > mevers_dto필드명
            String member_availability_ID = String.format("valid_%s", error.getField());
            //error.getDefaultMessage() : 유효성 검사에 실패한 필드에 정의된 메시지를 가져옵니다.
            availability_ID.put(member_availability_ID, error.getDefaultMessage());
        }
        return availability_ID;
    }
    //회원가입
    @Override
    public void insertMember(Member member) {
        System.out.println("--------회원가입---------");
        memberRepo.save(member);
    }


}
