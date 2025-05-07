package com.example.hello_springboot.dto;

import java.time.LocalDateTime;

public class HelloResponse {
    private String message;
    private LocalDateTime timestamp;

    public HelloResponse(String message,LocalDateTime timestamp){
        this.message = message;
        this.timestamp = timestamp;
    }

    public HelloResponse(String message) {
        this.message = message;
    }

    // getter
    public String getMessage() {
        return message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

