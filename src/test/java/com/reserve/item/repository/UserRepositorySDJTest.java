package com.reserve.item.repository;

import com.reserve.item.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class UserRepositorySDJTest {

    @Autowired
    UserRepository jpaRepo;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원 등록 & 이름으로 찾기")
    public void 회원추가_찾기() {
//        User user = User.createUser("my id", "lsh", "1234", "heheh22Dd");
//        userRepo.save(user);

        em.flush();
        em.clear();

        User find = jpaRepo.findByName("idid2").orElse(null);
        if(find != null) System.out.println("find.getName() = " + find.getName());
        else System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
    }



}