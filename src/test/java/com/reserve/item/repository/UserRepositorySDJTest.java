package com.reserve.item.repository;

import com.reserve.item.domain.User;
import com.reserve.item.repository.impl.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositorySDJTest {

    @Autowired
    UserJpaRepository jpaRepo;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원 등록 & 이름으로 찾기")
    public void 회원추가_찾기() {
//        User user = User.createUser("my id", "lsh", "1234", "heheh22Dd");
//        userRepo.save(user);

        em.flush();
        em.clear();

        User find = jpaRepo.findOne("idid2").orElse(null);
        if(find != null) System.out.println("find.getName() = " + find.getName());
        else System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
    }



}