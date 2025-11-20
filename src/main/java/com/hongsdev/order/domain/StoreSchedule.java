package com.hongsdev.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
public class StoreSchedule {

    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    private DayOfWeek dayOfWeek;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;





}
