package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class HellowController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World->" + nameAges.toString();
    }

    private static HashMap<String, Integer>nameAges = new HashMap<>();
    @PostMapping("/hello")
    public String helloPost(String name, int age) {
        nameAges.put(name,age);
        return "add name:" + name + ",age= " + age;
    }

    @PutMapping("/hello")
    public String helloPut(String name, int age) {
        nameAges.put(name,age);
        return "update name:" + name + ",age= " + age;
    }

    @DeleteMapping("/hello")
    public String helloDelete(String name) {
        nameAges.remove(name);
        return "Hello World->" + nameAges.toString();
    }


}
