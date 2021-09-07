package com.reserve.item.controller;

import com.reserve.item.domain.User;
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

        String id = userRegisterForm.getId();
        String name = userRegisterForm.getName();
        String password = userRegisterForm.getPassword();
        String email = userRegisterForm.getEmail();

        Long returned;
        User user = User.createUser(id,name,password,email);

        returned = userService.join(user);
        return returned == 0L ? "DUPLICATE" : "OK";
    }


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserLoginForm userLoginForm) {
        if (userService.login(userLoginForm.getId(), userLoginForm.getPassword())) {
            return "ok";
        } else {
            return "wrong password or id";
        }
    }
}
