package com.example.demo.Entity.board;
/**
* @package : com.example.demo.domain
* @name : Board.java
* @date : 2022-08-08 오후 6:18
* @author : Rubisco
* @version : 1.0.0
* @modifyed :
* @description : 게시판 도메인
**/

import com.example.demo.Entity.account_info.Member;
import com.example.demo.Entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String category;

    @Column(length = 40, nullable = false, unique = true)
    private String title;

    @Column(length = 40, nullable = false, updatable = false)
    private String writer;

    //member를 필드에 선언
    //참조키가 어디인지 선언해줘야함 (member 기본키가 board의 참조키로 기본적으로 할당되어있음)
    //member의 id는 기본키가 아니기 때문에 수정을 해줘야함 -> board의 writer는 member의 id와 연관되어 있고 잠조키로 id로 연결되어있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Member member;



    @Setter
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

    //2022.08.22
    //@ManyToOne 다양한 board는 1개의 member를 바라본다.
    //@OneToMany는 member 1튜플마다 여러개의 board를 가진다는 속성 선언과 다수 엔티티 연동에

    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private Long cnt;



}
