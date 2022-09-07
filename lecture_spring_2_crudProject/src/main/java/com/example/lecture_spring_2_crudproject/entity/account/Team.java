//package com.example.lecture_spring_2_crudproject.entity.account;
//
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Team {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "team_id")
//    private Long id;
//
//    private String name;
//
//    //컬렉션 페치 조인 설명을 위해 양방향 연관 관계 설정
//    @OneToMany(mappedBy = "team")
//    private List<User> users = new ArrayList<>();
//
//    public Team(String name) {
//        this.name = name;
//    }
//}
