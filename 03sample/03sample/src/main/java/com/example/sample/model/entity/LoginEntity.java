package com.example.sample.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SequenceGenerator(
        name="LOGIN_ID_SEQ_GEN",//시퀀스 제너레이터 이름
        sequenceName="LOGIN_ID_SEQ",//시퀀스 이름
        initialValue=1,//시작값
        allocationSize=1//메모리를 통해 할당할 범위 사이즈
)
public class LoginEntity {

    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,//사용할 전략을 시퀀스로  선택
            generator="LOGIN_ID_SEQ_GEN"//식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;
}
