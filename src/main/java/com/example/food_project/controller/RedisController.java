package com.example.food_project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisTemplate resdisTemplate;

    @GetMapping()
    public ResponseEntity<?> saveDataToRedis(){
        resdisTemplate.opsForValue().set("keyA", "Hello Reris");
        String data = (String) resdisTemplate.opsForValue().get("keyA");
        System.out.println("Kiểm tra data " + data);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
