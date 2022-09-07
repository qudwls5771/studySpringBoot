package com.example.lecture_spring_2_crudproject.service.textTransfer;

import org.springframework.stereotype.Service;

@Service
public class TextTransfer {

    public String transferText3Word(String text) throws Exception {

        //java 문자열치환 내장메서드 : split, subString..
        String wordFirst3 = text.substring(0,3);
        System.out.println("앞의 3글자 = "+wordFirst3);
        String wordLast = text.substring(4,text.length());
        System.out.println("뒤의 나머지 글자 = "+wordLast);
        //substring과 split 구조 차이
        //subString : 원본 배열을 참조해서 순번과 길이만 가지고 자름 (객체를 따로 생성해서 관리 x)
        //split : 새로운 인스턴스를 만들어서 문자열을 자르고, 더불어 결과값을 String배열로 받아옴 (객체를 따로 생성해서 관리)

        //replaceAll 메서드의 변경 할 값에 "."을 쓰면 모든 문자를 지정
        wordLast = wordLast.replaceAll(".", "*");
        System.out.println(wordFirst3);
        System.out.println(wordLast);

        System.out.println(wordFirst3+wordLast);

        //split example
//        String str = "js code example 20220828";
//
//        String[] result = str.split(" ");
//        String[] result2 = str.split(" ", 2);
//        String[] result3 = str.split(" ", 3);
//
//        System.out.println(Arrays.toString(result));
//        System.out.println(Arrays.toString(result2));
//        System.out.println(Arrays.toString(result3));

        //substring example
//        String str = "Hi student. This is split example";
//        String result = str.substring(17);
//        String result2 = str.substring(17, 22);
//
//        System.out.println(result);
//        System.out.println(result2);

        //substring indexof
//        String str = "This is macbook is beautiful";
//        int beginIndex = str.indexOf("is");
//        int endIndex = str.length();
//        String result = str.substring(beginIndex, endIndex);
//
//        System.out.println(result);

        //replace example (. = 모든 문자)
//        SString a = "무궁화. 삼천리. 화려강산. 대한사람. 대한으로. 길이. 보전하세 ";
////replaceAll([정규식],[바꿀문자])
//        a = a.replaceAll(".", "/");
//        System.out.println(a);



//        text.replace()
        return wordFirst3 + wordLast;
    }
}
