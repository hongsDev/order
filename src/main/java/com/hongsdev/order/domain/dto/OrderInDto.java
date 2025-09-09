package com.hongsdev.order.domain.dto;

import com.hongsdev.order.domain.Address;
import lombok.Data;

@Data
public class OrderInDto {
    private Long memberId;

    private String city;
    private String street;
    private String zipcode;

}
