package com.example.hello_springboot.service;

import com.example.hello_springboot.dto.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getHelloMessage() {
        return "Hello, Spring Boot from Service";
    }
    public String getPersonalizedMessage(String name){
        return "Hello," + name + "!";
    }

    public HelloResponse getPersonalizedResponse(String name){
        return new HelloResponse("Hello, " + name + "!");
    }
}
