package com.reserve.item.service;

import com.reserve.item.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    UserService userService;

    @Test
    public void 회원찾기() throws Exception {
        User user = new User();
        user.setEmail("lsh@naver.com");
        user.setName("hihi");
        userService.join(user);
        em.flush();

        Optional<User> finduser = userService.findUserByEmail("lsh@naver.com");
        assertThat(user.getName()).isEqualTo("hihi");
    }


    @Test
    public void findUser() throws Exception{
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

    @Test
    public void 유저없음() throws Exception {
        Optional<User> found = userService.findUserById("sfjdoitba");
        assertThat(found).isEqualTo(null);

//        assertThrows(Exception.class ,() -> userService.findUserById("sfjdoitba"));
    }
}