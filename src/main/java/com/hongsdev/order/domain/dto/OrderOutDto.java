package com.hongsdev.order.domain.dto;

import com.hongsdev.order.domain.enumType.DeliveryStatus;
import com.hongsdev.order.domain.enumType.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOutDto {

    private Long orderId;
    private String username;
    private OrderStatus orderStatus;
    private DeliveryStatus deliveryStatus;

}
