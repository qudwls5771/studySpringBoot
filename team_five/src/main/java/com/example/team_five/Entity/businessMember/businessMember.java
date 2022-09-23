package com.example.team_five.Entity.businessMember;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class businessMember {
    @Id
    @Column(name = "member_id", length = 20, nullable = false, unique = true)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{8,16}", message = "아이디는 8~16글자이며, 영문 소문자를, 숫자를 반드시 포함시켜주세요.")
    private String id;

    @Column(name = "member_password", length = 18)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자를 포함한 특수문자를 사용하세요.")
    private String password;

    @Column(name = "member_name")
    private String name;

    @Column(name = "business_Number")
    private String businessNumber;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Column(name = "member_email")
    private String email;

    @Column(name = "member_phone")
    @Pattern(regexp = "(?=.*[0-9]).{11}", message = "핸드폰 번호를 다시확인해주세요!.")
    private String phone;

    @Column(name= "member_address", length = 50)
    private String address;

    @Column(name = "member_gender", length = 4)
    private String gender;

    @Column(name = "member_year")
    @Pattern(regexp = "(?=.*[0-9])(?=.*\\W)(?=\\S+$).{8,16}", message = "생년월일을 다시 확인해주세요.")
    private String year;
}
