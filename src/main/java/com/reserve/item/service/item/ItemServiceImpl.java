package com.reserve.item.service.item;

import com.reserve.item.domain.Item;
import com.reserve.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemByPk(Long item_pk) {
        return itemRepository.findById(item_pk).orElse(null);
    }
}
