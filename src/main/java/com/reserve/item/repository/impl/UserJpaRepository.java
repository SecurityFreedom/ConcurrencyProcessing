package com.reserve.item.repository.impl;

import com.reserve.item.domain.User;
import com.reserve.item.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJpaRepository implements UserRepository {
    private final EntityManager em;


    public void save(User user) {
        em.persist(user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(em.createQuery("select u from User u where u.email =:email", User.class)
                .setParameter("email",email)
                .getSingleResult());
    }

    public List<User> findAll(){
        return em.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    public Optional<User> findOne(String id){
        try {
            List<User> findUser = em.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class)
                    .setParameter("id", id).getResultList();
            return findUser.size() > 0 ? Optional.ofNullable(findUser.get(0)) : Optional.empty();
        }catch(javax.persistence.NoResultException e){
            return Optional.empty();
        }
    }
}
