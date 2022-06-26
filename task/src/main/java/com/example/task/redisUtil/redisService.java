package com.example.task.redisUtil;

import java.util.concurrent.TimeUnit;

public interface redisService<K,V> {
    void redisPut(K key,V value);
    void redisPut(K key,V value,Long time , TimeUnit timeUnit);
    V redisGet(K key);
    void redisPutNoTime(K key,V value);
    void redisDeleteByKey(K key);
    Long redisGetTimeByKey(K key);
}
