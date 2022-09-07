package com.human.pet.domain;

//외장 라이브러리 (gradle로 다운로드한 롬북이 외장 라이브러리)

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
//롬북에 있는 Getter라는 메서드를 통해
//하단에 있는 클래스 board는
//자동으로 getter, setter 메서드가 생성됨을
//암시함(@어노테이션)

@Entity
//Entity가 이 class가 JPA를 통해 데이터베이스 테이블로로 쓰겠다 => 컬럼으로 쓰겠다. 선언!


@ToString


public class Board {

    //식별 필드
    // @ID : Primary key = 유일한 값 설정
    // @GeneratedValue :  자동생성 속성
    @Id
    @GeneratedValue
    private Long seq;

    //@Column은 title 필드값을 컬럼화할 때 길이와 null 입력 가능여부 확인 옶션
    @Column(length = 40, nullable = false)
    private String title;
     @Column(updatable = false)
    private String writer;


    @Column(nullable = false)
    //@ColumnDefault 생성할 때 기본 데이터
    @ColumnDefault("'no content'")
    private String content;

//    @Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
    private Date createDate;
//    @Column(insertable = false, updatable = false, columnDefinition = "number default 0")
    private long cnt;
    private String categori;
    
    //원래는 setter, getter라는 메서드가 있어야 private 필드값에 데이터를 넣을 수 있지만,
    //(gradle에서 설치)롬북 이라는 라이브러리로 자동 getter, setter 메서드 생성
}