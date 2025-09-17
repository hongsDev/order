package com.hongsdev.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderItem> orderItems = new ArrayList<>();

    private String name;
    private int stock;

    public void addStock(int count) {
        this.stock += count;
    }

    public void removeStock(int count) {
        this.stock -= count;
    }


}
