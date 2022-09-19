package com.example.security.Service;

import com.example.security.Entity.Member;
import com.example.security.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceimpl implements MemberService{


    private final MemberRepo memberRepo;

    @Autowired
    protected MemberServiceimpl(MemberRepo memberRepo){
        this.memberRepo = memberRepo;
    }

    //회원가입
    @Override
    public void insertMember(Member member) {
        System.out.println("--------회원가입---------");
        memberRepo.save(member);
    }

    @Override
    public Member getMember(Member member) {
        Optional<Member> findMember = memberRepo.findById(member.getId());
        if(findMember.isPresent())
            return findMember.get();
        else return null;
    }
}
