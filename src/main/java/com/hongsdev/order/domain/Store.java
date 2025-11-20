package com.hongsdev.order.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    private Long id;

    private String name;

    private String address;

    private String description;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();


    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreSchedule> storeSchedules = new ArrayList<>();
}
