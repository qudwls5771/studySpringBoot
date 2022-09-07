package textTransfer;

public class TextTransfer {
                                            //도중에 에러뜨는 거 방지
    public String transferText3Word(String text) throws Exception{

        //java 묹자열치환 내장메소드 : split, subString...
        String wordFirst3 = text.substring(0, 3);
        System.out.println("앞의 3글자 : " + wordFirst3);
        String wordLast = text.substring(4, text.length());
        System.out.println("뒤의 3글자 : " + wordLast);
        //subString과 split의 구조차이
        //subString : 원본 배열을 참조해서 순번과 길이만 가지고 자름(객체를 따로 생성해서 관리X)
        //split : 새로운 인스턴스를 만들어서 문자열을 자르고, 더불어 결과값을 String배열로 받아옴 (객체를 따로 생성관리)

        //replaceAll메소드의 변경할 값에 "."을 쓰면 모든 문자를 지정
        wordLast = wordLast.replaceAll(".", "*");
        System.out.println(wordFirst3);
        System.out.println(wordLast);
        System.out.println(wordFirst3+wordLast);

        return wordFirst3+wordLast;
    }

}
