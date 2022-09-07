package com.example.demo.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    //CRUD restFullApi rest하게 API를 만들자 (암묵적인 규칙)
    //Create, read, Update, delete
    //data를 전달하는 Controller URL만들기


    @ResponseBody
    @RequestMapping("/read/alldata")
    public CustomAPIDtoExample readAlldata(){

    }

}
