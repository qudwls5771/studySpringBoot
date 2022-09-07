package com.example.demo.service.board;

/**
* @package : com.example.demo.service
* @name : BoardService.java
* @date : 2022-08-08 오후 6:21
* @author : Rubisco
* @version : 1.0.0
* @modifyed : 
* @description : 게시판 서비스
**/

import com.example.demo.Entity.account_info.Member;
import com.example.demo.Entity.board.Board;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList(Board board);

    void insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(Board board);




    //작성자의 모든 게시글 출력
    List<Board> getBoardListAllByMemberId(Member member);



    //board의 작성자와 회원이 같은지 확인 [2,5조...]
    boolean booleanMemberIdEqualsBoardWriterByMember(Member member);

    //키워드분석 [4조]
    List<String> doNounsAnalysis(List<Board> boardList);

    //관련된 키워드 게시글 출력
    List<Board> getAutoKeywordBoardList(List<String> keyword);

    //오름차순으로 변경 (arrayList)
    List<Board> getBoardListSortColumnByBoardList(List<Board> boardlist);


//------------------------코멘트---------------------------------------------------------

   // List<Comments> getAllComments(Comments comments);

   // void insertComment(Comments comments);
}
