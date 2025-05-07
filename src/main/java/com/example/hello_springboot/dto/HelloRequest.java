package com.example.hello_springboot.dto;

import jakarta.validation.constraints.NotBlank;

public class HelloRequest {
    @NotBlank(message = "名前は必須です。")
    private String name;

    //    getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
