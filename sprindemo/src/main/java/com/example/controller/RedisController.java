package com.example.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/redis")
public class RedisController {

    private RedisTemplate redisTemplate;


    @PostMapping("/addredis")
    public  String  insertRedis(String key){
              redisTemplate.opsForValue().append("name",key);
              return  "ok";
    }
}
