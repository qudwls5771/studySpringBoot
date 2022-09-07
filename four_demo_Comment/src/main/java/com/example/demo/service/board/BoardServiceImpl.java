package com.example.demo.service.board;

/**
* @package : com.example.demo.service
* @name : BoardServiceImpl.java
* @date : 2022-08-08 오후 6:23
* @author : Rubisco
* @version : 1.0.0
* @modifyed : 
* @description : 게시판 서비스 구현체
**/

import com.example.demo.Entity.account_info.Member;
import com.example.demo.Entity.board.Board;
import com.example.demo.persistence.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//JPA가 @Service로 선언된 클래스를 갖고 JDBC에게 기능적인 구현을 위한 속성
@Service
public class BoardServiceImpl implements BoardService {

    //BoardRepository에 있는 DB와 연동하여 기능하는 것을 명시
    private final BoardRepository boardRepo;


    //순환참조 중단
    @Autowired
    protected BoardServiceImpl(BoardRepository boardRepo) {
        this.boardRepo = boardRepo;
    }

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의 findAll메서드를 통해서
    //전체 조회
    @Override
    public List<Board> getBoardList(Board board) {
        return (List<Board>) boardRepo.findAll();
    }


    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의 Save메서드를 통해서
    //DB에 저장 (저장하는 SQL문 만들어서 실행)
    @Override
    public void insertBoard(Board board) {
        System.out.println("서비스 : "+board);
        boardRepo.save(board);
    }

    @Override
    public Board getBoard(Board board) {
        return boardRepo.findById(board.getSeq()).get();
    }

    @Override
    public void updateBoard(Board board) {
        Board findBoard = boardRepo.findById(board.getSeq()).get();

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepo.save(findBoard);
    }

    @Override
    public void deleteBoard(Board board) {

        boardRepo.deleteById(board.getSeq());
    }

    @Override
    public boolean booleanMemberIdEqualsBoardWriterByMember(Member member) {
        return false;
    }

    @Override
    public List<Board> getBoardListAllByMemberId(Member member) {
        //Repository
        System.out.println("------getBoardListByMemberId-----");
        System.out.println(member.getId());
        return boardRepo.findAllByMemberIdEqualsBoardWriter(member.getId());
    }

    @Override
    public List<String> doNounsAnalysis(List<Board> boardList) {
        return null;
    }

    @Override
    public List<Board> getAutoKeywordBoardList(List<String> keyword) {
        return null;
    }

    @Override
    public List<Board> getBoardListSortColumnByBoardList(List<Board> boardlist) {
        return null;
    }



    //-------------------------코멘트--------------------------------------------------------



}
