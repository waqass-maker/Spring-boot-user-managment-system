

package com.springboot.mrspringboot;

import com.springboot.mrspringboot.serviceerepo.Emailservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest

public class testfile {
//    @Autowired
//    private Emailservice emailservice;

//    @Test
//    void testEmail() {
//        emailservice.send("211370011@gift.edu.pk", "Waqas bro", "It is amazing Waqas");
//    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSendmail(){
        redisTemplate.opsForValue().set("email","waqas@email.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a=1;
    }
}
