package com.example.hello_springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class HelloRequest {
    @NotBlank(message = "名前は必須です。")
    @Schema(description = "あいさつ対象の名前。空欄不可。")
    private String name;

    //    getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
