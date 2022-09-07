package com.example.testspring.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

import java.util.Date;

@Getter
@Setter
@ToString
public class Member {
    private String id; //아이디
    private String pwd; // 비밀번호
    private String name; // 이름
    private String year;  // 생년월일(사람)
    private String phone; // 핸드폰 번호
    private String address; // 주소
    private String pet_T; // 펫종류
    private String pet_S; // 펫 성별
    private String pet_D; // 생년월일(펫)
    private Integer pet_W; // 펫 몸무게
    private Date join_D; // 회원가입일자
    private Date join_O; // 회원 탈퇴일자
    private String join_M; // 회원가입 상태(가입 : N, 탈퇴 : Y)
}
