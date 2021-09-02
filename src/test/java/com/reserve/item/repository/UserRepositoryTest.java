package com.reserve.item.repository;

import com.reserve.item.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepo;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원 등록 & 이름으로 찾기")
    public void 회원추가_찾기() {
        User user = User.createUser("my id", "lsh", "1234", "heheh22Dd");
        userRepo.save(user);

        em.flush();
        em.clear();

        User find = userRepo.findByName("lsh").get();
        assertThat(find.getId()).isEqualTo(user.getId());
    }

}