package com.hongsdev.order.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value,30,  TimeUnit.MINUTES);
    }

    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }



}
