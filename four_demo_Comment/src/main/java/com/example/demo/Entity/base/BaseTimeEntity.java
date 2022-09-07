package com.example.demo.Entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

//JPA Entity 클래스들이 BaseEntity를 상속할 때 createdDate,modifiedDate 도 컬럼으로 인식
//BaseEntity에 Auditing 기능을 포함 시킨다.
//@EntityListeners 는 리스너 클래스 지정을 통해 엔티티 객체 상태가 변경될 때 해당 리스너로 콜백을 받는다.
//AuditingEntityListener 는 JPA 내부에서 엔티티 객체가 생성/변경 되는 것을 감지


//날짜를 나오게 하는 것.
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateDate;
}
