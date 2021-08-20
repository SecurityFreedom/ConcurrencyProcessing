package com.reserve.item.repository;

import com.reserve.item.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findByEmail(String email) {
        return em.createQuery("select u from User u where u.email =:email", User.class)
                .setParameter("email",email)
                .getSingleResult();
    }


}
