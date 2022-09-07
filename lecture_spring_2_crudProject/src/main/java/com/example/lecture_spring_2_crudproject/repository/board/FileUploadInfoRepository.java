package com.example.lecture_spring_2_crudproject.repository.board;

import com.example.lecture_spring_2_crudproject.entity.data.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//FileUploadEntity를 저장하는 인터페이스 (JPA CrudRepository)
public interface FileUploadInfoRepository extends JpaRepository<FileUploadEntity, Long> {

    //findBy : 튜플을 찾는다.
    //BoardSeq : board_seq 매개변수
    FileUploadEntity findByBoardSeq(Long boardSeq);

}
