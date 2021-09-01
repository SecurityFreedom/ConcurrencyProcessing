package com.reserve.item.repository;

import com.reserve.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositorySDJ extends JpaRepository<Item, Long> {

}
