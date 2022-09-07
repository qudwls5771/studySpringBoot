package com.example.demo.service.account_info;

import com.example.demo.Entity.account_info.Member;
import com.example.demo.persistence.account_info.MemberRepository;
import com.example.demo.service.encrypt.EncryptAES256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberServiceImpl implements memberService{

    @Autowired
    //persistence.account_info => MemberRepository에 있는 CrudRepository<Member, Long> 사용
    private MemberRepository memberRepo;

    private final EncryptAES256 encryptAES256;

    //순환참조 중단
    @Autowired
    protected memberServiceImpl(MemberRepository memberRepo, EncryptAES256 encryptAES256) {
        this.encryptAES256 = encryptAES256;
        this.memberRepo = memberRepo;
    }


    //public : 공개
    // List<Member> : 리턴타입 = List , 속성은 Member
    //return memberRepository의 findMemberByEmailorId메소드를 실행한 리턴 데이터
    @Override
    public Member getMemberWhereIdOrEmail(String email, String id) {
        return memberRepo.findMemberByEmailOrId(email, id);
    }

    @Override
    public List<Member> findEmail(String email) {
        return memberRepo.findEmail(email);
    }

    @Override
    public Member getMemberWhereIdAndROWNUL1(String id) {
        return memberRepo.findFirstById(id);
    }

    //모든 회원의 정보를 가져다 오는 것.
    //return List<Member> : 모든 회원의 정보를 List배열에 담아서 return
    //public : 모두 공개하는 메소드
    //List<Member> : 이 메소드가 실행되면 return되는 타입
    //(List<Member>)  : 뒤에 있는 결과값을 형변환
    //memberRepo : @Autowired MemberRepository를 통해 기능 실행
    //findAll() : memberRepo에 있는 모든 정보 가져오기 메소드 실행
    @Override
    public List<Member> getMemberList() {
        //ArrayList를 List로 형변환!
        List<Member> memberList =  memberRepo.findAll();

        for(int i =0; i<memberList.size(); i++){
            StringBuilder stb = new StringBuilder();
            for(int j =0; j < memberList.get(i).getEmail().length(); j++){
                String a = memberList.get(i).getEmail();
                if( j > 2){
                    stb.append("*");
                }
                else {
                    stb.append(a.charAt(j));
                }
            }
            memberList.get(i).setEmail(String.valueOf(stb));
            System.out.println(memberList);
        }
        return memberList;
    }
    //회원 1명의 정보를 Entity에 맞춰서 DB에 저장
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member getMember(Member member) {
                                //형변환을 해야한다. member.getSeq() 그래서 member.getSeq().get()
        return memberRepo.findById(member.getSeq()).get();
    }

    @Override
    public void updateMember(Member member) {
        //비밀번호 바꾸기
        //1. seq를 통해서 튜플 정보를 모두 가져오기
        //2. 가져온 튜플 정보 중 수정할 내용 적기
        //3. DB에 저장
        //findById().get() : 고유키 기준으로 튜플 전체 데이터 가져오기
        Member memberUpdate = memberRepo.findById(member.getSeq()).get();
        //튜플 전체 내용중에 ID/비밀번호를 수정(setter)
        memberUpdate.setId(member.getId());
        memberUpdate.setEmail(member.getEmail());
        //crudRepo의 save 메소드를 통해 데이터 저장
        memberRepo.save(memberUpdate);

        //고유키
        //1. 튜플을 식별할 수 있는 값 (데이터 한 줄)
        //2. 다른 테이블의 튜플과 연동하기 위한 값(join, 외래키)
        //3. 객체지향 방법으로 DB저장
        //3-1. 영속성(Durability) : DB의 영구성
        //3-2. 고립성(Isolation ) :
        //3-2-1. 관리자 1은 seq 10의 회원정보를 바꿨습니다.
        //이미 접속해 있던 관리자2가 seq10 회원의 정보를 조회
        //seq10의 회원 정보를 바꾸는 작업이 한 개의 트랜잭션
        //관리자2의 sseq10회원의 정보를 조회하고 수정하는 작업도 한 개의 트랜잭션
        //관리자1의 트랜잭션 작업이 완료될때까지 관리자2의 seq10회원정보는 옛날 정보를 조회하고 있고,
        //관리자1의 트랜잭션 작업이 완료되는 순간까지 관리자2는 seq10회원정보를 수정할 수 없다.
        //다른 필드값은 수정이 가능해도, seq는 튜플의 식별자로써 수정이 불가해야, 관리자(1,2)의 트랜잭션
        //작업을 스트링부트에서 구분할 수 있다.

        //3-3
    }

    @Override
    public void deleteMember(Member member) {
        memberRepo.deleteById(member.getSeq());
    }

    @Override //아이디 중복
    public void signUp(Member member) {

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
}
