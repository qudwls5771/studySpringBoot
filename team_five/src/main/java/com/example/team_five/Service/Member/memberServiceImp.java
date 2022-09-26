package com.example.team_five.Service.Member;

import com.example.team_five.Entity.Member.Member;
import com.example.team_five.Repository.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class memberServiceImp implements MemberService{


    private final MemberRepository memberRepo;


    @Autowired
    protected  memberServiceImp(MemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }



    @Override
    public void insertMember(Member member) {
        System.out.println("-------회원가입------");
        Member findMember = memberRepo.findMemberById(member.getId());
        if(findMember != null){ //멤버에 아이디가 있을 경우 앞에 중복된 아이디라고 출력문이 나온다.
            System.out.println("중복된 아이디 입니다.");
        }else{ // 중복이 안된 경우 회원가입
            memberRepo.save(member);
        }
    }

    @Override //   회원 수정
    public void updateMember(Member member) {
        Member updateMember = memberRepo.findById(member.getId()).get();
        updateMember.setPassword(member.getPassword());
        updateMember.setAddress(member.getAddress());
        updateMember.setPhone(member.getPhone());
        updateMember.setEmail(member.getEmail());

        System.out.println(updateMember.getPassword());
        System.out.println(updateMember.getAddress());
        System.out.println(updateMember.getPhone());
        System.out.println(updateMember.getEmail());

        memberRepo.save(updateMember);
    }


    //아이디 유효성 검사
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
    //회원탈퇴
    @Override
    public void deleteMember(Member member) {
        memberRepo.deleteById(member.getId());
    }

    @Override
    public Member getMember(Member member) {
        // 특정회원을 검색하여 리턴하고, 만약 검색 결과에 없으면 null을 리턴한다.
        // 로그인시에도 활용한다.
        Optional<Member> findMember = memberRepo.findById(member.getId());
        if(findMember.isPresent())
            return memberRepo.findById(member.getId()).get();
            else return null;
    }
}
