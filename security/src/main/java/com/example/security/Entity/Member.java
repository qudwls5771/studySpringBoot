package com.example.security.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    private String id;
    private String password;
    private String name;
    private String role = "ROLE_USER";
}
