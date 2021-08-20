package com.reserve.item.repository;

import com.reserve.item.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findAll(){
        return em.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    public Optional<User> findOne(String id){
        try {
            return Optional.ofNullable(em.createQuery("SELECT u FROM User u WHERE u.id=:id",User.class)
                    .setParameter("id",id).getSingleResult());
        }catch(javax.persistence.NonUniqueResultException e){
            return null;
        }
    }
}
