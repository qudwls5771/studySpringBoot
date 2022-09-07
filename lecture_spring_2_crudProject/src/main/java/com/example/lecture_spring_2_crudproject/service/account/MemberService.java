package com.example.lecture_spring_2_crudproject.service.account;


import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.entity.customDto.CustomDtoExample;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberService {

    //Email또는ID를 조회하여 튜플을 찾기
    Member getMemberWhereIdOrEmail(String Email, String Id);

    Member getMemberWhereIdAndROWNUL1(String id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member Member);

    //일부분만 검색하여 사용유저 찾기 [3조]
    //결과값 : 입력받은 정보(email, id, pw)가 유사사실유무 확인 후 비밀번호 변경(updateMember의 password)
    boolean booleanSearchUserByEmail(Member member);
    boolean booleanSearchUserById(Member member);
    boolean booleanSearchUserByPassword(Member member);

    //***별표처리 MemberList (replace..) [6조]
    List<Member> getMemberListEmailSecurityStarByMemberList(List<Member> memberlist);

    //민감데이터 (SHA256..)
    List<Member> getMemberListEncodingByMemberList(List<Member> memberlist);


    //30일 지난 회원에게 변경 페이지 안내 [1조]
    boolean booleanAfter30DaysChangePasswordByMemberUpdateDate(Member member);

    //비밀번호 변경 테이블 생성 후 변경한 기록을 남긴 뒤, 변경 내용 최신 3회 내용과 비교
    boolean booleanChangedPassword3CheckByMemberPassword(Member member);

    List<Member> getMemberListAndBoardListByMemberId(String memberId);

    CustomDtoExample getCustomDtoByMemberId(String memberId);

}
