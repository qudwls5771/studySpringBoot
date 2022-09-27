package com.example.team_five.Repository.Member;

import com.example.team_five.Entity.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {

    //아이디 찾기 = 중복, 회원 아이디 찾기에도 사용됨
    @Query(value = "select m from Member m where m.id = :id")
    Member findMemberById(String id);




}
