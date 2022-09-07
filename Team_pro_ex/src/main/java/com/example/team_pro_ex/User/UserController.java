package com.example.team_pro_ex.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/signup")
    public String dispSignup(UserDto userDto) {
        return "/signup";
    }

    @PostMapping("/signup")
    public String execSignup(@Valid UserDto userDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("userDto", userDto);

            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/signup";
        }

        userService.signUp(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displogin() {
        return "/login";
    }
}
