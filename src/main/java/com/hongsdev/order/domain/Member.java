package com.hongsdev.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "member")
    public List<Order> orders = new ArrayList<>();


    public void addOrder(Order order) {
        orders.add(order);
    }


    public static Member createMember(String username, String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);

        return member;
    }
}
