package com.example.demo.springboot.redis;

public interface RedisService {

    //add
    void set(String id, String name);
    //add
    void setForever(String id, String name,long time);
    //select
    String get(String id);
    //update
    void update(String id, String name);
    //delete
    void delete(String id);
}
