package com.hongsdev.order.domain;

import com.hongsdev.order.domain.enumType.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="ORDER_INFO")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public void setMember(Member member) {
        this.member = member;
        member.addOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public static Order createOrder(Member member, Delivery delivery) {
        Order order = new Order();

        order.setMember(member);
        order.setDelivery(delivery);
        order.setStatus(OrderStatus.ORDER);

        return order;
    }
}
