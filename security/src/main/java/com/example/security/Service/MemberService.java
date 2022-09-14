package com.example.security.Service;

import com.example.security.Entity.Member;
import org.springframework.stereotype.Service;


public interface MemberService {
    public void insertMember(Member member);

    public Member getMember(Member member);
}
