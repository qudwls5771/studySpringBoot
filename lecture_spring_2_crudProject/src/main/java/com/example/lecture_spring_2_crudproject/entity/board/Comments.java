package com.example.lecture_spring_2_crudproject.entity.board;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String comments_content;

    @Transient
    private String board_title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_title" ,referencedColumnName = "title")
    private Board board;

    public Comments(String comments_content, Board board) {
        this.comments_content = comments_content;
        this.board = board;
    }
}
