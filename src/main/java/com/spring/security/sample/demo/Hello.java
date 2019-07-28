package com.spring.security.sample.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello!";
    }

    @GetMapping("/hello2")
    public String sayHello2() { return "Hello2!";}

}
