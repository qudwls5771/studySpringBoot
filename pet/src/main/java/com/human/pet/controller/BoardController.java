package com.human.pet.controller;
import javax.servlet.http.HttpServletRequest;

//외장 라이브러리 호출(import), gradle로 설치한 라이브러리

import com.human.pet.Service.BoardService;
import com.human.pet.domain.Board;
import com.human.pet.domain.Categori;
import com.human.pet.domain.Comment;
import com.human.pet.persistence.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//내장 라이브러리 호출(import)
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class BoardController {

    //CRUD
    //중복코드 처리리


    //step1. 일반 문자열 변수 사용
    static String title_string_static = "";
    static String writer_string_static = "";
    static String content_string_static = "";

    static String categori_String_static = "";




    //step2. 배열 객체 사용
    static ArrayList<String> title_array = new ArrayList<String>();
    static ArrayList<String> writer_array = new ArrayList<String>();
    static ArrayList<String> content_array = new ArrayList<String>();

    static ArrayList<String> categori_array = new ArrayList<String>();


    //step3. 사용자 생성 객체 사용
    static ArrayList<Board> board_array = new ArrayList<Board>();
    static int count = 0;


    //코멘트
    static ArrayList<Comment> comment_array = new ArrayList<Comment>();

    static String comment_string_static = "";




//    @RequestMapping은 서버에서 디스페처서블릿을 통해 html의 action태그의 주소와 동일한
//    문자열을 찾는 매핑기능(연결)이 실행되고 하단에 메서드가 실행
//    return String인 이유는 뷰리졸버가 html파일을 찾기 위한 문자열 리턴

    //클라이언트에서 서버로 무언가 데이터를 전송하기 위한 Mapping(@RequestMapping)


    @RequestMapping("/getBoardList")
    public String getBoardList(Model model) {
        //List 타입으로 Board객체를 넣는 boardList변수명 선언
        // = (대입연사자)로 heap메모리에 ArrayList타입으로 할당
        //List는 ArrayList의 부모클래스

       /* String outputString = title_string;
        String outputString_static = title_string_static;
        System.out.println("----------------");
        System.out.println(outputString);
        System.out.println(outputString_static);*/
        List<Board> boardList = new ArrayList<Board>();

        //title_array.size()로 게시판 글이 1개이상일 경우에만 model에 데이터 입력하여
        //[클라이언트]에 전달

        //매개변수를 넣어서 간결하게 만든다.
        if (board_array.size() > 0) {
            for (int i = 0; i < board_array.size(); i++) {
                //Board 클래스로 board인스턴스 생성
                Board board = new Board();
                //롬북으로 자동생성된 seter 메서드로 데이터 입력
                board.setSeq(board_array.get(i).getSeq());
                board.setCategori(board_array.get(i).getCategori());
                //매개변수 title_array.get(i)은
                //BoardController의 필드인
                //title_array, writer_array, content_array의
                //값을 순회하여 출력 (get(i));
                //board.setter를 통해서 board객체에 데이터 입력
                board.setTitle(board_array.get(i).getTitle());
                board.setWriter(board_array.get(i).getWriter());
                board.setContent(board_array.get(i).getContent());
                //내장 클래스인 java.util.Date 객체로 시간 데이터 출력
                board.setCreateDate(board_array.get(i).getCreateDate());
                board.setCnt(board_array.get(i).getCnt());
                //boardList배열에 board객체 넣기(for문 10번 도니까 board객체 10개 넣기)
                boardList.add(board);
            }
        }

        //model 객체에 boardList(arrayList)를 boardList key값으로 넣음
        //attributeName = key
        //attributeValue = value
        //model에는 참조타입만 넣을 수 있다(addAttribute 메서드 안에 매개변수 타입으로 확인 가능)
        model.addAttribute("boardList", boardList);
        //디서패처서블릿이 뷰 리졸버를 찾아서 연결해 줍니다.
        //viewResolver
        //return getBoardList라는 문자열로 templates에 있는 같은 이름에 html파일을 찾는다.
        return "getBoardList"; //서버
    }
    //@GetMapping 또는@PostMapping은 @RequestMapping의 자식 클래스이다
    //RequestMapping의 기능을 모두 쓸 수 있다
    //자식클래스 어노테이션이 아닌 부모클래스 어노테이션을 쓰는 이유는 기능의 한정을 통해서
    //서버의 리소스 감소 및 보안을 위해서 이다

    //Create
    @GetMapping("/insertBoard")
    public String insertBoard() {
        return "insertBoard";
    }

    //[클라이언트]html form태그의 method속성의 값인 post를 인식하여 아래의
    //@PostMapping에 연결
    @PostMapping("insertBoard")
    public String insertBoard(
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content,
            @RequestParam("categori") String categori,
            Model model) {


        title_string_static = title;
        writer_string_static = writer;
        content_string_static = content;
        categori_String_static = categori;

        title_array.add(title);
        writer_array.add(writer);
        content_array.add(content);
        categori_array.add(categori);

        count++;
        //클래스나 메소드로 만든다.
        Board board = new Board();

        board.setSeq((long) count);
        board.setCategori(categori);
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);
        board.setCreateDate(new Date());
        board.setCnt(0L);
        board_array.add(board);

        System.out.println("Board : " + board);
        System.out.println("Board_Array : " + board_array);
        //redirect : 페이지 전환 이동
        //redirect:getBoardList : getBoardList 페이지로 이동
        return "redirect:getBoardList";
    }


    //@어노테이션은 메서드 혹은 클래스에 속성, 정의를 해서 스프링이나 자바에서 찾기 쉽도록 해주는 선언부
    //예) @Override 은 부모 메서드를 재정의하여 사용한다고 자바나 스프링에게 속성 명시
    //@RequestParam : [클라이언트]에서 string문자열을 [서버]에 전달하는 매개변수 선언
    //@RequestParam("title")String title에서 ("title")은 [클라이언트]의 name이라는 속성로써
    //key값을 매개변수를 전달

    //Read = Select
    @GetMapping("/getBoard")
    public String getBoard(
            @RequestParam("seq") String seq,
            @RequestParam("userRole") String userRole,
            @RequestParam("userId") String userId,
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content,
            @RequestParam("createDate") String createDate,
            @RequestParam("cnt") String cnt,
            Model model) {
        //클래스나 메소드로 만든다.
        List<Comment> commentLists = new ArrayList<Comment>();
        for (Comment comment : comment_array) {
            if (Long.toString(comment.getSeq()).equals(seq)) {
                commentLists.add(comment);
            }
        }
        //CommentList에 있는 seq, comment를 가져온다.
        model.addAttribute("comments", commentLists);
        model.addAttribute("seq", seq);
        model.addAttribute("title", title);
        model.addAttribute("writer", writer);
        model.addAttribute("content", content);
        model.addAttribute("createDate", createDate);
        model.addAttribute("cnt", cnt);
        model.addAttribute("userId", userId);
        model.addAttribute("userRole", userRole);

        return "getBoard";
    }






    @PostMapping("/insertComment")
    public String insertComment(@RequestParam("comments") String comments,
                                @RequestParam("seq") Long seq,
                                HttpServletRequest request,
                                Model model) {
        comment_string_static = comments;
        Comment comment = new Comment();
        comment.setComments(comments);
        comment.setSeq(seq);
        comment_array.add(comment);
        String referer =  request.getHeader("referer");
        return "redirect:" + referer;
    }

    //Delete
    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("seq") String seq) {
        //seq매개변수 (getBoard.html에서 받아옴)로 board_array 배열에서
        //.getSeq로 같은 index를 찾아
        //board_array에 있는 board객체 삭제 >> 원하는 게시글 삭제
        for (int i = 0; i < board_array.size(); i++) {
            //board_array.get(i).getSeq() : board_array의 i번째 객체를 찾아서 getseq()메소드
            //를 통해 seq필드값 가져오기
            //equals()메소드를 통해서 매개변수 seq값과 비교(참조타입)
            //seq 타입은 Long입니다, 소수점 있는 데이터(숫자)이므로 매개변수 seq(String)과
            //같은 타입이 아니므로 비교불가
            //board_array.get(i).getSeq()의 값 Long을 String으로 변환 = Long.toString()
            if (Long.toString(board_array.get(i).getSeq()).equals(seq)) {
                // System.out.println("같음확인");
                // System.out.println(seq);
                //System.out.println(board_array.get(i).getSeq());
                board_array.remove(i);
                System.out.println("글삭제 : " + board_array);
            }
        }
        return "redirect:getBoardList";

    }
    @RequestMapping("categoris") //카테고리 분류
    public String categorize(@RequestParam("categori") String categori, Model model){
        //Board => 새로운 ArrayList를 만든다.
        ArrayList <Board> caterori = new ArrayList<>();
        for(int i=0; i<board_array.size(); i++){
            //equals를 이용해서 분류 선택 = 자유, 상품, 문의사항 중에 1개를 선택해서
            //그에 해당하는 게시글을 보여준다.
            if(Objects.equals(board_array.get(i).getCategori(), categori)){
                caterori.add(board_array.get(i));
            }
        }

        model.addAttribute("boardList", caterori);

        return "getBoardList";
    }


    //update
    //Post 방식으로 [클라이언트]에서 [서버]로 Mapping
    @PostMapping("/updateBoard")
    public String updateBoard(
            //Html에서 name속성을 가진 값을 매개변수 String seq에 할당 = @RequestParam("seq")
            @RequestParam("seq") String seq,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        System.out.println("Update Board Access");
        //board_array배열을 순회하여 board객체의 seq필드값을 매개변수 seq와 비교하여 true값 찾기
        for (int i = 0; i < board_array.size(); i++) {
            if (Long.toString(board_array.get(i).getSeq()).equals(seq)) {
                //setTitle과 같은 setter로 데이터 변경
                board_array.get(i).setTitle(title);
                board_array.get(i).setContent(content);
                System.out.println("수정완료 : " + board_array);
            }
        }
        return "redirect:getBoardList";
    }
   @PostMapping()
    public String boardHits(@RequestParam("cnt") String cnt){
        ArrayList<Board> hitsList = new ArrayList<Board>();
        for(int i = 0; i<board_array.size(); i++){
        }


        return "getBoardList";
    }



    /**
     *     @GetMapping("/getBoardList")
     *     public String getBoardList(Model model){
     *             model.addAttribute("boardList", boardService.getBoardList());
     *         return "getBoardList";
     *     }
     *
     *     @GetMapping("/getBoard")
     *     public String getBoard(Board board, Model model){
     *         model.addAttribute("board", boardService.getBoard(board));
     *         return "getBoard";
     *     }
     * **/




}
