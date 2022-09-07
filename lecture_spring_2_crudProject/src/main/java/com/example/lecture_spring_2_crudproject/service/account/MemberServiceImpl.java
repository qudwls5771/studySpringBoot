package com.example.lecture_spring_2_crudproject.service.account;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.entity.customDto.CustomDtoExample;
import com.example.lecture_spring_2_crudproject.repository.account.MemberRepository;
import com.example.lecture_spring_2_crudproject.repository.customRepository.CustomDtoExampleRepository;
import com.example.lecture_spring_2_crudproject.service.encrypt.EncryptAES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepo;

    private final EncryptAES256 encryptAES256;
    private final CustomDtoExampleRepository customDtoExampleRepository;

    //순환참조 중단
    @Autowired
    protected MemberServiceImpl(MemberRepository memberRepo, EncryptAES256 encryptAES256, CustomDtoExampleRepository customDtoExampleRepository) {
        this.encryptAES256 = encryptAES256;
        this.memberRepo = memberRepo;
        this.customDtoExampleRepository = customDtoExampleRepository;
    }


    //public : 공개
    //List<Member> : 리턴타입은 List 속성은 Member
    //retrun memberRepository의 findMemberByEmailOrId메서드를 실행한 리턴 데이터
    @Override
    public Member getMemberWhereIdOrEmail(String Email, String Id) {
        return memberRepo.findMemberByEmailOrId(Email, Id);
    }

    @Override
    public Member getMemberWhereIdAndROWNUL1(String id) {
        return memberRepo.findFirstById(id);
    }

    //모든 회원의 정보를 가져다 오는 것
    //return List<Member> : 모든 회원의 정보를 List배열에 담아서 return
    //public : 모두 공개하는 메서드
    //List<Member> : 이 메서드가 실행되면 return되는 타입
    //(List<Member>) : 뒤에 있는 결과값을 형변환
    //memberRepo : @Autowired MemberRepository 를 통해 기능 실행
    //findAll() : memberRepo에 있는 모든 정보 가져오기 메서드 실행
    @Override
    public List<Member> getMemberList() {
        return (List<Member>) memberRepo.findAll();
    }

    //회원 1명의 정보를 Entity에 맞춰서 DB에 저장
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member getMember(Member member) {
        return memberRepo.findById(member.getSeq()).get();
    }

    @Override
    public void updateMember(Member member) {
        //1. seq를 통해서 튜플 정보를 모두 가져오기
        //2. 가져온 튜플 정보 중 수정할 내용 적용
        //3. DB에 저장 (덮어쓰기)
        //findById().get() : 고유키 기준으로 튜플 전체 데이터 가져오기
        Member findMember = memberRepo.findById(member.getSeq()).get();
        //튜플 전체 내용 중에 ID/email주소 수정 (setter)
        findMember.setId(member.getId());
        findMember.setEmail(member.getEmail());
        //crudRepo의 save 메서드를 통해 데이터 저장
        memberRepo.save(findMember);

        //고유키
        //1. 다른 튜플을 식별할 수 있는 값 (데이터 한 줄) : DB관점에서 보는 것
        //2. 다른 테이블의 튜플과 연동하기 위한 값 (JOIN, 외래키) : DB관점
        //3. 객체지향 방법으로 DB를 저장
        //3-1. 영속성 : DB에 영구저장
        //3-2. 고립성 : 다른 트랜젝션 작업에 연관되지 않도록 해주는 것
        //3-2 : 관리자1은 seq 10의 회원정보를 바꿨습니다. 이미 접속해 있던 관리자2가 seq10 회원의 정보를 조회하고 수정.
        //seq10의 회원정보를 바꾸는 작업이 한 개의 트랜젝션. 관리자2의 seq10회원의 정보를 조회하고 수정하는 작업도 한 개의 트랜젝션.
        //관리자1의 트랜젝션 작업이 완료될 때까지 관리자2의 seq10회원정보는 옛날 정보를 조회하고 있고,
        //관리자1의 트랜젝션 작업이 완료되는 순간까지 관리자2는 seq10회원정보를 수정할 수 없다.
        //다른 필드값은 수정이 가능해도, seq는 튜플의 식별로자로써 수정이 불가해야, 관리자1,2의 트랜젝션 작업을 스프링부트에서 구분할 수 있다.
    }

    @Override
    public void deleteMember(Member member) {
        memberRepo.deleteById(member.getSeq());
    }

    @Override
    public boolean booleanSearchUserByEmail(Member member) {
        for(Member member1 : memberRepo.findByEmailContaining(member.getEmail())) {
            System.out.println(member1.getId());
        }
        return false;
    }

    @Override
    public boolean booleanSearchUserById(Member member) {
        for(Member member1 : memberRepo.findByIdContains(member.getId())) {
            System.out.println(member1.getId());
        }
        return false;
    }

    @Override
    public boolean booleanSearchUserByPassword(Member member) {
        for(Member member1 : memberRepo.findByPasswordIsContaining(member.getPassword())) {
            System.out.println(member1.getId());
        }
        return false;
    }

    @Override
    public List<Member> getMemberListEmailSecurityStarByMemberList(List<Member> memberList) {
        for(Member member : memberList) {
            try {
                //예제
                String str = "ABCDEFG"; //대상 문자열
                /*A=0 B=1 C=2 D=3 E=4 F=5 G=6의 index를 가진다.*/

                str.substring(3);
                /*substring(시작위치) 결과값 = DEFG*/

                str.substring(3, 6);
                /*substring(시작위치,끝위치) 결과값 = DEF*/
                member.setPassword(encryptAES256.encrypt(member.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<Member> getMemberListEncodingByMemberList(List<Member> memberList) {
        for(Member member : memberList) {
            try {
                member.setPassword(encryptAES256.encrypt(member.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return memberList;

    }

    @Override
    public boolean booleanAfter30DaysChangePasswordByMemberUpdateDate(Member member) {
        return false;
    }

    @Override
    public boolean booleanChangedPassword3CheckByMemberPassword(Member member) {
        return false;
    }

    @Override
    public List<Member> getMemberListAndBoardListByMemberId(String memberId) {
        return memberRepo.findAllByMemberIdEqualsBoardWriter(memberId);
    }

    @Override
    public CustomDtoExample getCustomDtoByMemberId(String memberId) {
        return customDtoExampleRepository.findExample(memberId);
    }


}
