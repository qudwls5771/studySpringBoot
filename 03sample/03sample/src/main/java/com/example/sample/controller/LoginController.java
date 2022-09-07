package com.example.sample.controller;

import com.example.sample.model.entity.LoginEntity;
import com.example.sample.model.request.LoginDTO;
import com.example.sample.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/insert")
    public void LoginInsert(){

    }

    @PostMapping("/insert")
    public String LoginInsert(LoginDTO dto, Model model){
        System.out.println(dto);

//        LoginEntity entity = new LoginEntity(null, dto.getName(), dto.getPassword());
//        LoginEntity entitySaved = loginRepository.save(entity);

        LoginEntity entitySaved = loginRepository.save(dto.toEntity());
//        System.out.println(entitySaved);
        log.info(String.valueOf(entitySaved));

        return "redirect:/selectOne/"+entitySaved.getId();
    }

    @GetMapping("/selectOne/{id}")
    public String SelectOne(@PathVariable Integer id, Model model){

        LoginEntity dto = loginRepository.findById(id).orElse(null);
        model.addAttribute("dto", dto);
        System.out.println(dto);
        return "selectOne";
    }

    @GetMapping("/selectAll")
    public String SelectAll(Model model){

        ArrayList<LoginEntity> dtos = loginRepository.findAll();
        model.addAttribute("dtos", dtos);
        System.out.println(dtos);
        return "selectAll";
    }

    @GetMapping("/delete/{id}")
    public String Delete(@PathVariable Integer id, RedirectAttributes rttr){

        LoginEntity dto = loginRepository.findById(id).orElse(null);
        if(dto!=null){
            loginRepository.delete(dto);
            rttr.addFlashAttribute("msg","success");
        }

        return "redirect:/selectAll";
    }

    @GetMapping("/update/{id}")
    public String UpdateGET(@PathVariable Integer id, Model model){

        LoginEntity dto = loginRepository.findById(id).orElse(null);
        model.addAttribute("dto", dto);

        return "update";
    }

    @PostMapping("/update")
    public String UpdatedPOST(LoginDTO dto){

        LoginEntity entity = dto.toEntity();

        loginRepository.save(entity);

        return "redirect:/selectOne/"+entity.getId();
    }
}
