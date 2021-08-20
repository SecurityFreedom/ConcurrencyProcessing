package com.reserve.item.controller;

import com.reserve.item.domain.User;
import com.reserve.item.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final UserService userService;

    @RequestMapping("/req")
    @ResponseBody
    public String reqAns(@RequestParam("name") String name) {
        User user = new User();
        user.setName(name);
        user.setEmail("heheisi@ia.com");
        user.setId("idid");
        user.setPassword("12345");
        userService.join(user);


        return "ok";
    }
}
