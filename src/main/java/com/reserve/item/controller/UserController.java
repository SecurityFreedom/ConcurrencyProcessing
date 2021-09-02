package com.reserve.item.controller;

import com.reserve.item.domain.User;
import com.reserve.item.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = {"/", "/home"})
    public String loginPage(Model model) {
        return "home";
    }

    @PostMapping(value = {"/", "/home"})
    public String loginProcess(UserLoginForm userLoginForm) {
        String id = userLoginForm.getId();
        String password = userLoginForm.getPassword();
        Long userId = userService.login(id, password);

        if (userId > 0) {
            return "main";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(UserRegisterForm userRegisterForm) {
        String id = userRegisterForm.getId();
        String name = userRegisterForm.getName();
        String password = userRegisterForm.getPassword();
        String email = userRegisterForm.getEmail();

        Long userId = userService.join(User.createUser(id,name,password,email));
        if(userId>0){
            return "redirect:/";
        }else{
            return "redirect:/register";
        }
    }
}
