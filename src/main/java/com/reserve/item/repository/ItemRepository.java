package com.reserve.item.repository;

import com.reserve.item.domain.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();
}
