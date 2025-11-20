package com.hongsdev.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    private Long id;

    private String username;

    private String age;

    private String phoneNumber;


    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();
}
