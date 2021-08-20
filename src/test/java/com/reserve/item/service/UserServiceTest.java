package com.reserve.item.service;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class UserServiceTest {
    @Autowired
    UserService userService;


    @Test
    void findUserById() {
        // GIVEN
        User user = new User();
        user.setId("re12io");
        user.setEmail("re12io@naver.com");
        user.setPassword("dbstlr41");
        user.setName("강윤식");

        // WHEN
        userService.join(user);

        // THEN
        assertThat(userService.findUserById(user.getId())).isPresent();
    }
}