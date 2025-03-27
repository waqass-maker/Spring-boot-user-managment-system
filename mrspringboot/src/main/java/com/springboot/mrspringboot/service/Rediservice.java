package com.springboot.mrspringboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mrspringboot.Waqas.weatherApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class Rediservice {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> weatherApiClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                log.warn("Cache miss for key: {}", key);
                return null;  // Return null or handle the cache miss differently
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), weatherApiClass);
        } catch (Exception e) {
            log.error("There is an issue in Redis get operation", e);
            return null;
        }
    }

    public void set(String key,Object o, Long ttl ){
        try {
            ObjectMapper mapper=new ObjectMapper();
            String jaso=mapper.writeValueAsString(o);
          redisTemplate.opsForValue().set(key,jaso,ttl, TimeUnit.SECONDS);
        }
        catch (Exception e){
            log.error("there is issue in it ",e);
        }

    }
}
