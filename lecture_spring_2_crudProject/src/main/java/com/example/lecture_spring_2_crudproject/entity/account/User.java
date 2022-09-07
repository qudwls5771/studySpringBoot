//package com.example.lecture_spring_2_crudproject.entity.account;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class User {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String name;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "team_id")
//    private Team team;
//
//    public User(String name, Team team) {
//        this.name = name;
//        this.team = team;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", team=" + team +
//                '}';
//    }
//}
