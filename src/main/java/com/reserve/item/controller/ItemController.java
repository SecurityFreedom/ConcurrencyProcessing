package com.reserve.item.controller;

import com.reserve.item.domain.Item;
import com.reserve.item.dto.ItemDTO;
import com.reserve.item.repository.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/items")
    @ResponseBody
    public List<ItemDTO> items() {
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (Item item : itemRepository.findAll()){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setPk(item.getPk());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setCategory_name(item.getCategory().getName());
            itemDTOList.add(itemDTO);
        }

        return itemDTOList;
    }
}