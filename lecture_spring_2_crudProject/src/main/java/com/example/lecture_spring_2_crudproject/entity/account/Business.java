//package com.example.lecture_spring_2_crudproject.entity.account;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import static javax.persistence.CascadeType.ALL;
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//public class Business {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "Business_ID")
//    private Long id;
//
//    private String name;
//
//    @OneToMany(mappedBy = "business", cascade = ALL )
//    private List<DetailInfo> detailInfoList = new ArrayList<>();
//
//    public void addDetailInfo(DetailInfo detailInfo) {
//        detailInfoList.add(detailInfo);
////        detailInfo.setDetailInfo(this);
//    }
//}