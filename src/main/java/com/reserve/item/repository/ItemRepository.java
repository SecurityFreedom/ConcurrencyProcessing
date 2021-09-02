package com.reserve.item.repository;

import com.reserve.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findAll();
}
