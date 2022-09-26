package com.example.team_five.Service.Member;

import com.example.team_five.Entity.Member.Member;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {


    void insertMember(Member member);

    void updateMember(Member member);

    public Map<String, String> member_Availability(Errors errors);

    void deleteMember(Member member);

    public Member getMember(Member member);

}
