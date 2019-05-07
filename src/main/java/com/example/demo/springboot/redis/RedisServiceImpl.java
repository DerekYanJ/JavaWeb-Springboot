package com.example.demo.springboot.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService  {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(String id, String name) {
        redisTemplate.opsForValue().set(id, name);
    }

    @Override
    public void setForever(String id, String name, long time) {

        redisTemplate.opsForValue().set(id, name);
        redisTemplate.expire(id, 360, TimeUnit.DAYS);
    }


    @Override
    public String get(String id) {
        return redisTemplate.opsForValue().get(id);
    }


    @Override
    public void update(String id, String name) {
        redisTemplate.opsForValue().set(id, name);
    }


    @Override
    public void delete(String id) {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        vo.getOperations().delete(id);
    }

}
