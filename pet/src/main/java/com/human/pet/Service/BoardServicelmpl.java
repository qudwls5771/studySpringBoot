package com.human.pet.Service;

import com.human.pet.Service.BoardService;
import com.human.pet.domain.Board;
import com.human.pet.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//JPA가 @Service 로 선언된 클래스를 갖고 JDBC에게 기능적인 구현을 위한 속성
@Service
public class BoardServicelmpl implements BoardService {

    @Autowired
    private BoardRepository boardRepo;
    //BoardRepository에 있는 기능을 DB와 연동하여 기능하는 것을 명시

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의
    // findAll메소드를 통해서 전체 조회
    @Override
    public List<Board> getBoardList(Board board) {
        return (List<Board>) boardRepo.findAll();
    }

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의 Save메소도를 통해서
    //DB에 저장 (저장하는 SQL문 만들어서 실행)
    @Override
    public void insertBoard(Board board) {
        boardRepo.save(board);
    }

    @Override
    public Board getBoard(Board board) {
        return null;}
    //boardRepo.findAllById(board.getSeq()).get();

    @Override
    public void updateBoard(Board board) {

    }

    @Override
    public void deleteBoard(Board board) {

    }
}
