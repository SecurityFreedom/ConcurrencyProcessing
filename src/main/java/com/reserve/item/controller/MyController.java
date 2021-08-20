package com.reserve.item.controller;

import com.reserve.item.domain.User;
import com.reserve.item.dto.UserDTO;
import com.reserve.item.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final UserService userService;

}
