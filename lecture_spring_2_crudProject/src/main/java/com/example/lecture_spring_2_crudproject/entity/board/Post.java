//package com.example.lecture_spring_2_crudproject.entity.board;
//
//import com.example.lecture_spring_2_crudproject.entity.account.User;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Builder
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//public class Post {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @Column
//    private String title;
//
//    @Column
//    private String description;
//
//}
