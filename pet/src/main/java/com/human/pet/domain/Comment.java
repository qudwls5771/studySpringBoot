package com.human.pet.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue
    private Long seq;
    private String comments;
}
