package com.example.demo.Entity.account_info;

import com.example.demo.Entity.base.BaseTimeEntity;
import com.example.demo.Entity.board.Board;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@AllArgsConstructor : 모든 매개변수를 갖는 생성자
//@NoArgsConstructor(access = AccessLevel.PROTECTED) : 매개변수 없는 생성자
//@Builder

//@Entity JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
    name = "User_Seq-Gen",
    sequenceName = "User_Seq",
    initialValue = 1, // 1부터 시작
    allocationSize = 1 //사이즈가 1부터 증가 1->2->3
)
public class Member extends BaseTimeEntity implements Serializable {

    //SELCT [*컬럼명=객체의 필드] FROM TABLE_NAME*객체;
    //CREATE TABLE (
//        seq NUMBER primary key,
//        id  VARCHAR2(40) NOT NULL
//    )
    //JPA : 객체에 맞춰서 SQL문으로 바꿔주는 것 (번역)
    //@Id : table을 만들 때, 테이블의 튜플(row)를 식별할 수 있는 기본키

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //*영속화 => 객체를 어딘가에 영원히 저장하겠다.
    //JVM밖에서도 객체를 영원히 저장하고 싶다.
    //Commit, flush, persist를 포괄하는 내용
    //SQL(MyBatis)는 DB틀에 맞춘 mapper라고 정의한다면,
    //JPA는 객체(Entity, 튜플)단위로 DB에 저장하는 개념을 영속화 한다고 정의

    //*IDENTITY : DB에 필드값을 저장 후에 기본키를 생성
    //AUTO_INCREMENT처럼 DB에 값을 저장하고 나서야 기본키를 구할 수 있을 때 쓰임
    // = DB에 클라이언트에서 받은 정보를 저장 후에 기본키(ex)seq를 부여
    //Entity가 영속상태가 되기 위해서는 식별자가 필수
    //em : EntityManager
    //em.psersiste()를 하는 즉시 insert SQL(기본키 저장)이 DB에전달
    //필드값을 테이블에 저장함과 동시에 기본키생성해서 집어 넣는다.
    //트랜잭션이 지원하는 쓰기 지연이 동작 x
    //빈번하게 저장할 때 좋다 = ex)게시판 댓글


    //*Sequence : DB(Oracle) Sequence 함수 기는ㅇ을 활용하여 생성
    //DB마다 index를 생성하고 관리하는 함수가 있음
    //시퀀스 전략은 em.persist()를 호출할 때 먼저 DB 시퀀스를 사용해서 식별자를 조회
    //이후 트랜잭션 커밋 시점에 플러시가 발생하면 엔티티를 DB에 저장
    //빈번하게 저장할 때 한번에 저장할 때 좋다.


    //*Table : Seq(시퀀스)를 정보로 갖고 있는 테이블을 만들고,
    // seq칼럼값을 저장한 뒤 불러온다.
    //여타 위에 전략과 달리 임의의 seq Table을 만들기 때문에
    // table 성능이 좋지 않을경우(튜닝하지 않을 경우)
    //속도적인 문제를 야기할 수 있다.

    //Sequence 최적화 전략
    //allocationSize 시퀀스 접근하는 횟수를 줄이기 위한 방편
    //예를 들어, allocationSize가 50이라면, 시퀀스 함수 한번 조회 시
    //50씩 증가하고, 1~50사이에서는 메모리에서 식별자로 할당.
    //백엔드(서버)마다 DB를 조회해서 여러 서버가 동시에 접근하고 시퀀스 함수를
    //사용하여 시퀀스를 할당할 때 1단위로 size가 증가하면 DB저장에 문제를
    // 야기할 수 있으므로 size를 넘게 잡아 메모리가 알아서 접속한 서버마다 할당 해주는 전랴
    //~50으로 시퀀스 값을 선정하므로 여러 JVM(스프링 부트 서버)가 동시에
    // 동작해도 기본 키 값이 충돌하지 않는 장점, DB부하를 피할 수 있다.
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE
//                    generator ="User_Seq-Gen")
//    private Long seq;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    //table 끼리 조인을 하는 조건
    //1. Member.id는 member의 튜플마다 유일한 값 (유니크 키)
    //member마다 게시글(board)를 쓸 수 있다는 조건이 있으므로,
    //board입장에서는 member의 id가 유일해야지 식별할 수 있다
    //2. null처리 (null이 들어가면 board는 Id를 식별할 수 없다)
    @Column(length = 40, nullable = false, unique = true)
    private String id;





    //member는 여러개의 board를 가질 수 있다고 선언,
    //board들을 가지고 있다고 필드에 넣음 (JPA는 이 필드내용으로 테이블 연관관계(JOIN)으로 식별)
    //@OneToMany는 member 1튜플마다 여러개의 board를 가진다는 속성 선언과 다수 엔티티 연동에
    // Springboot는 Serializable 상속 요구함

    @BatchSize(size= 100)
    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();

    private String password;


    private String email;

    //deleteYn

}
