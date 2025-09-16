package com.hongsdev.order.domain;

import jakarta.persistence.*;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

@Entity
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    public void addItem(Item item) {
        this.item = item;
        item.getOrderItems().add(this);
    }

    private int count;
    private int price;

    public static OrderItem createOrderItem(Item item, int count, int price) {
        OrderItem orderItem = new OrderItem();
        orderItem.addItem(item);
        orderItem.setCount(count);
        orderItem.setPrice(price);

        return orderItem;
    }

}
