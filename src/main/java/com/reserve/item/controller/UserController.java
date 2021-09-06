package com.reserve.item.controller;

import com.reserve.item.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = {"/register"})
    @ResponseBody
    public String processTest(
            @RequestBody UserRegisterForm userRegisterForm){
        System.out.println("userRegisterForm = " + userRegisterForm);
        return "ACCEPT!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }

}
