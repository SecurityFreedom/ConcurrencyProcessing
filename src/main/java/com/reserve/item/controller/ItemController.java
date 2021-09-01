package com.reserve.item.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @GetMapping("/items")
    public List<Item> items() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("ldsa", 25));
        return itemList;
    }

    @Getter
    static class Item {
        private String name;
        private int age;

        public Item(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}