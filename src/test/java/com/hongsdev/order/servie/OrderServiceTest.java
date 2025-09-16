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
    public void 주문저장() {
        OrderInDto order = new OrderInDto();
        order.setMemberId(1L);
        order.setCity("test1");
        order.setStreet("street");
        order.setZipcode("zipcode");

        Long orderId = orderService.save(order);
        Assertions.assertThat(orderId).isNotNull();
    }


    @Test
    public void 주문취소() {
        Long orderId = 1L;
        OrderInDto orderInDto = new OrderInDto();
        orderInDto.setOrderId(orderId);

        orderService.cancel(orderInDto);


    }

    @Test
    public void 전제조회() {
        orderService.findAll();
    }

    @Test
    public void 회원주문내역조회() {
        OrderInDto order = new OrderInDto();
        order.setMemberId(52L);
        orderService.findByMember(order);
    }
}