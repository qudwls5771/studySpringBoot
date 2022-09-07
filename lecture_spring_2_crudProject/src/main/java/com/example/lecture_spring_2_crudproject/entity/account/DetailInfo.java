//package com.example.lecture_spring_2_crudproject.entity.account;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//public class DetailInfo {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "DetailInfo_ID")
//    private Long id;
//
//    private String content;
//
//    @ManyToOne
//    @JoinColumn(name = "DetailInfo_ID")
//    private Business business;
//}