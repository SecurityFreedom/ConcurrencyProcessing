package com.reserve.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private long pk;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    private String name;
    private int quantity;
    private int price;

    public static Item createItem(Category category, int quantity, int price, String name){
        Item item = new Item();
        item.setCategory(category);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setName(name);
        return item;
    }

    public void sell(){
        this.quantity -= 1;
    }
}
