package com.reserve.item.repository.impl;

import com.reserve.item.domain.Item;
import com.reserve.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemJpaRepository implements ItemRepository {
    @Autowired
    EntityManager em;

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }
}
