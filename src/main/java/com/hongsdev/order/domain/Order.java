package com.hongsdev.order.domain;

import com.hongsdev.order.domain.enumType.DeliveryStatus;
import com.hongsdev.order.domain.enumType.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDER_INFO")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

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

    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItem) {

        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setStatus(OrderStatus.ORDER);

        for (OrderItem orderItem1 : orderItem) {
            order.addOrderItem(orderItem1);
        }

        return order;
    }


    //취소 비즈니스 메소드
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료");
        }

        this.status = OrderStatus.CANCEL;
        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }

    }

}
