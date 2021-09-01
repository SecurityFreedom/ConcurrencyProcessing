package com.reserve.item.repository.impl;

import com.reserve.item.domain.Item;
import com.reserve.item.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemJpaRepository implements ItemRepository {
    @Override
    public List<Item> findAll() {
        return null;
    }
}
