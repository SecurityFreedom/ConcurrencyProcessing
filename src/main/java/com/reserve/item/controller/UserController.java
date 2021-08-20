package com.reserve.item.controller;

import com.reserve.item.domain.User;
import com.reserve.item.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value={"/", "/home"})
    public String loginPage(Model model) {
        return "home";
    }

    @PostMapping(value={"/", "/home"})
    public String loginProcess(UserLoginForm userLoginForm) {
        Optional<User> user = userService.findUserById(userLoginForm.getId());
        String password = userLoginForm.getPassword();
        System.out.println("userLoginForm password : " + password);
        return user.map(m -> {
            System.out.println("aa");
            return m.getPassword().equals(password) ? "main" : "redirect:/";})
                .orElseGet(() -> {
                    System.out.println("bb");
                    return "redirect:/";
                });
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(UserRegisterForm userRegisterForm) {
        Optional<User> user = userService.findUserById(userRegisterForm.getId());
        return user.map(m->"redirect:/register")
                .orElseGet(()->{
                    User newUser = new User();
                    newUser.setId(userRegisterForm.getId());
                    newUser.setName(userRegisterForm.getName());
                    newUser.setPassword(userRegisterForm.getPassword());
                    newUser.setEmail(userRegisterForm.getEmail());
                    userService.join(newUser);
                    return "redirect:/";
                });
    }
}
