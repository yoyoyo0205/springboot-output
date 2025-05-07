package com.example.hello_springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class HelloResponse {
    @Schema(description = "生成されたあいさつメッセージ")
    private String message;
    @Schema(description = "レスポンスが生成された日時（ISO 8601形式）")
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

