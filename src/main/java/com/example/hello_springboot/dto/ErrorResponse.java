package com.example.hello_springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;

public class ErrorResponse {
    @Schema(description = "エラーのメッセージ内容", example = "nameは空にできません")
    private String message;

    @Schema(description = "タイムスタンプ（エラー発生時刻）", example = "2025-05-07T12:34:56")
    private String timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getMessage() {
        return message;
    }

    public java.lang.String getTimestamp() {
        return timestamp;
    }
}
