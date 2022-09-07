package com.example.demo;

/*import com.example.demo.domain.Board;
import com.example.demo.persistence.BoardRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;*/

import com.example.demo.Entity.account_info.Member;
import com.example.demo.persistence.account_info.MemberRepository;
import com.example.demo.service.openTest.publicTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import textTransfer.TextTransfer;

@SpringBootTest
//@RunWith(SpringRunner.class)
//@SpringBootTest
class DemoApplicationTests {

    @Autowired
    publicTest apiTest_1;

    @Test
    void setApiTest_2(){
        apiTest_1.resultAPI();
    }
    //Test로 데이터 들어가는지 확인
    MemberRepository memberRepository;
    @Test
    @DisplayName("저장, 데이터 잘 들어갔는지 확인")
    void contextSave(){
        //setter로 엔티티를 생성하고 repository 정상 작동하는지 확인
        Member member = new Member();
        //큻라이언트가에서 Controller에 데이터를 전달하는 내용을 setter를 통해 대체
        member.setId("s");
        member.setPassword("s");
        member.setEmail("@@");
        memberRepository.save(member);

    }

    //문제3. 아이디 별처리
    TextTransfer textTransfer;
    @Test
    void textTest() throws Exception{
        textTransfer.transferText3Word("qudwls3771");
    }





//
//    @Autowired
//    private BoardRepository boardRepo;
//
//    @Before
//    public void dataPrepare() {
//        for (int i = 1; i <= 200; i++) {
//            Board board = Board.builder()
//                    .title("테스트 제목 " + i)
//                    .writer("테스터")
//                    .content("테스트 내용 " + i)
//                    .build();
//
//            boardRepo.save(board);
//        }
//    }

//     @Test
//     public void testInsertBoard() {
//         Board board = Board.builder()
//                         .title("테이블 제목")
//                         .writer("Rubisco")
//                         .content("첫번째 글입니다.")
//                         .build();
//
//         boardRepo.save(board);
//     }

//    @Test
//    public void testFindByTitle() {
//        List<Board> boardList = boardRepo.findByTitle("테스트 제목 10");
//
//        System.out.println("검색 결과");
//        for (Board board : boardList) {
//            System.out.println("---> " + board.toString());
//        }
//    }

}