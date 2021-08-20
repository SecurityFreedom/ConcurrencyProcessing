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

        user = userService.findUser("lsh@naver.com");
        Assertions.assertThat(user.getName()).isEqualTo("hihi");
    }
}