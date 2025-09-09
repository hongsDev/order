package com.hongsdev.order.servie;

import com.hongsdev.order.domain.Order;
import com.hongsdev.order.domain.dto.OrderInDto;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback(false)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void TEST1() {
        OrderInDto order = new OrderInDto();
        order.setMemberId(1L);
        order.setCity("test1");
        order.setStreet("street");
        order.setZipcode("zipcode");

        Long orderId = orderService.save(order);
        Assertions.assertThat(orderId).isNotNull();
    }

    @Test
    public void TEST2() {
        orderService.findAll();

    }

    @Test
    public void TEST3() {
        OrderInDto order = new OrderInDto();
        order.setMemberId(52L);

        orderService.findByMember(order);

    }
}