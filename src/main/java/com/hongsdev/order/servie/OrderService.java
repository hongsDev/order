package com.hongsdev.order.servie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongsdev.order.domain.*;
import com.hongsdev.order.domain.dto.MemberInDto;
import com.hongsdev.order.domain.dto.OrderInDto;
import com.hongsdev.order.domain.dto.OrderOutDto;
import com.hongsdev.order.repository.MemberRepository;
import com.hongsdev.order.repository.OrderRepository;
import com.hongsdev.order.util.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY = "orders";

    @Transactional
    public Long save(OrderInDto orderInDto) {
        Member member = memberRepository.findById(orderInDto.getMemberId()).orElseThrow();
        Delivery delivery = Delivery.createDelivery(new Address(orderInDto.getCity(), orderInDto.getStreet(), orderInDto.getZipcode()));
        Item item = new Item();
        item.setName("test");
        item.setStock(30);

        OrderItem orderItem = OrderItem.createOrderItem(item, 3, 3000);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = Order.createOrder(member, delivery, orderItems);
        orderRepository.save(order);

        return order.getId();
    }

    public void cancel(OrderInDto orderInDto) {
        Optional<Order> cancelOrder = orderRepository.findById(orderInDto.getOrderId());
        cancelOrder.orElseThrow().cancel();
    }

    public List<OrderOutDto> findAll() {
        try {
            String jsonValue = (String) redisTemplate.opsForValue().get(CACHE_KEY);
            if (jsonValue != null) {
                return objectMapper.readValue(jsonValue, new TypeReference<List<OrderOutDto>>() {
                });
            }

            List<OrderOutDto> result = orderRepository.findAll()
                    .stream()
                    .map(order -> new OrderOutDto(order.getId(), order.getMember().getUsername(), order.getStatus(), order.getDelivery().getStatus()))
                    .collect(Collectors.toList());

            String json = objectMapper.writeValueAsString(result);
            redisTemplate.opsForValue().set(CACHE_KEY, json, 10L, TimeUnit.MINUTES);

            return result;

        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void evictCache() {
        redisTemplate.delete(CACHE_KEY);
    }

    public List<OrderOutDto> findByMember(OrderInDto orderInDto) {
        return orderRepository.findByMemberId2(orderInDto.getMemberId())
                .stream()
                .map(order -> new OrderOutDto(order.getId(), order.getMember().getUsername(), order.getStatus(), order.getDelivery().getStatus()))
                .collect(Collectors.toList());
    }
}
