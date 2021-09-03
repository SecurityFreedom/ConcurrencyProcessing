package com.reserve.item.service.item;

import com.reserve.item.domain.Category;
import com.reserve.item.domain.Item;
import com.reserve.item.repository.CategoryRepository;
import com.reserve.item.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemServiceImplTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void getItems() {
        Category category = new Category();
        category.setName("테스트카테고리");
        categoryRepository.save(category);

        Item item = Item.createItem(category, 100, 39900);
        itemRepository.save(item);

        Item item2 = Item.createItem(category, 250, 19900);
        itemRepository.save(item2);

        assertThat(itemService.getItems().size()).isEqualTo(2);
    }
}