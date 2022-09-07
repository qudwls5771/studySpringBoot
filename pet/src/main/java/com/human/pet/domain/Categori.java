package com.human.pet.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity

public class Categori {
    @Id
    @GeneratedValue
    private  String code;
    private String displayName;


}
