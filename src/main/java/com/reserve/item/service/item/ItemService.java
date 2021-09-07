package com.reserve.item.service.item;

import com.reserve.item.domain.Item;
import java.util.List;

public interface ItemService {
    List<Item> getItems();
    Item findItemByPk(Long item_pk);
}
