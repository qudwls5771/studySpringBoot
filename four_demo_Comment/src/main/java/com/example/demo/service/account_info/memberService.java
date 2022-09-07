package com.example.demo.service.account_info;

import com.example.demo.Entity.account_info.Member;

import java.util.List;

public interface memberService {
    //Email 또는 ID를 조회하여 튜플을 찾기
    //한개만 출력 select List배열로 만들필요가 없다.
    Member getMemberWhereIdOrEmail(String Email, String Id);

    //LiKE를 사용한 이메일 찾기
    List<Member> findEmail(String email);

    Member getMemberWhereIdAndROWNUL1(String id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void  updateMember(Member member);

    void deleteMember(Member member);

    void signUp(Member member);

    //민감데이터 (SHA256..)
    List<Member> getMemberListEncodingByMemberList(List<Member> memberlist);




}
