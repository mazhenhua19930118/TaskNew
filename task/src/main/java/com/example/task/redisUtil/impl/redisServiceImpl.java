package com.example.task.redisUtil.impl;

import com.example.task.redisUtil.redisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service("redisService")
public class redisServiceImpl<K,V> implements redisService<K,V> {
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public void redisPut(K key, V value) {
        //redis配置的时间
        K keys = (K)"redis_limit_time";
        V time = redisGet(keys);
        if(null == time || "".equals(time)){
            redisPut(key,value,Long.valueOf(24),TimeUnit.HOURS);//默认存储24小时
        }else{
            redisPut(key,value,Long.valueOf(String.valueOf(time)),TimeUnit.HOURS);//根据配置存储
        }
    }

    @Override
    public void redisPut(K key, V value, Long time, TimeUnit timeUnit) {
        BoundValueOperations<Object,Object> ops = redisTemplate.boundValueOps(key);
        ops.set(value);
        ops.expire(time,timeUnit);

    }


    @Override
    public V redisGet(K key) {
        ValueOperations<Object,Object> operations = redisTemplate.opsForValue();
        return (V)operations.get(key);
    }

    @Override
    public void redisPutNoTime(K key, V value) {
        BoundValueOperations<Object,Object> ops = redisTemplate.boundValueOps(key);
        ops.set(value);
    }

    @Override
    public void redisDeleteByKey(K key) {
        if(redisTemplate.hasKey(key)){
            redisTemplate.delete(key);
        }
    }

    @Override
    public Long redisGetTimeByKey(K key) {
        BoundValueOperations<Object,Object> ops = redisTemplate.boundValueOps(key);
        return  ops.getExpire();
    }
}
