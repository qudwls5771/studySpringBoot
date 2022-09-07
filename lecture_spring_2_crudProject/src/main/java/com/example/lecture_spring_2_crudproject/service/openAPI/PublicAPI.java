package com.example.lecture_spring_2_crudproject.service.openAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import org.springframework.stereotype.Service;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

@Service
public class PublicAPI {

    public void testAPI() {

        String result = readAPI();

        //jacson : 빅데이터 등 큰 사이즈의 json처리
        //gson : 분산 아키텍쳐 설정 등 작은 용량의 json
        //simplejson : 골고루 빠름

        //json방식은 http 프로토콜을 통해서 데이터 전송규약(클라이언트와 백엔드 통신)
        //백엔드와 백엔드 통신도 json방식 > grpc 통신의 등작으로 다수의 백엔드 통신은 grpc 변경.(앱통신도 grpc)

        //Gson tranfer
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        String element = pretty.toJson(result);
        System.out.println("----------testAPI-----------");
        System.out.println(element);

        //String 문자열을 dto 객체로 변환
        //fromJson(문자열, DTO객체.class(런타임시점 객체))
        BusDTO busdto = pretty.fromJson(result, BusDTO.class);
        for(int i =0; i<busdto.getResponse().getBody().getNumOfRows(); i++) {
            System.out.println(busdto.getResponse().getBody().getItems().get(i).getCpname());
        }


    }

    public String readAPI() {
        String key = "%2Fk49W4UhNTuGlvyhZ6NCaHVhV1%2BBp0wbhWy0YjmvKgHQSFbVPwQqzw4ppSYg8O9ubHyLPYi8N%2F0e4yGvEQKGug%3D%3D";

        // 파싱한 데이터를 저장할 변수
        String bfResult = "";
        String brResult="";
        StringBuilder sb=new StringBuilder();

        try {

            URL url = new URL("http://apis.data.go.kr/B551177/BusInformation/getBusInfo?serviceKey="
                    + key + "&numOfRows=2&pageNo=1&area=1&type=json");

            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));


            while((brResult=br.readLine())!=null) {
                sb.append(brResult);
                System.out.println(brResult);
            }
            //자원 반환
            br.close();
            con.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }
}
