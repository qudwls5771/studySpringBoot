package com.example.lecture_spring_2_crudproject.controller.api;

import com.example.lecture_spring_2_crudproject.entity.customDto.CustomAPIDtoExample;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//api 데이터 전송 컨트롤로 메서드들은 @responsBody 속성 가짐
//@RestController
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test1")
    public CustomAPIDtoExample test1(){

        CustomAPIDtoExample blog = new CustomAPIDtoExample();

        blog.setTitle("테스트1");
        blog.setContent("테스트1 내용");

        return blog;

    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        JsonObject obj =new JsonObject();

        obj.addProperty("title", "테스트2");
        obj.addProperty("content", "테스트2 내용");

        JsonObject data = new JsonObject();

        data.addProperty("time", "12:00");

        obj.add("data", data);

        return obj.toString();
    }
}
