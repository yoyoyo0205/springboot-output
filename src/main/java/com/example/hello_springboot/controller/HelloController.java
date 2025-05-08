package com.example.hello_springboot.controller;

import com.example.hello_springboot.dto.ErrorResponse;
import com.example.hello_springboot.dto.HelloRequest;
import com.example.hello_springboot.dto.HelloResponse;
import com.example.hello_springboot.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "あいさつAPI",description = "名前を受け取ってあいさつメッセージを返すAPI群"
)
public class HelloController {
    @Autowired
    private HelloService helloService;

    @Operation(
            summary = "あいさつメッセージを返すAPI",
            description = "nameを指定してPOSTすると、その名前を含んだあいさつを返します。"
    )
    @RequestBody(
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "通常パターン",
                                    summary = "Airiさんにあいさつ",
                                    value = "{\"name\": \"Airi\"}"
                            ),
                            @ExampleObject(
                                    name = "空文字エラー",
                                    summary = "バリデーションエラーになる例",
                                    value = "{\"name\": \"\"}"
                            ),
                            @ExampleObject(
                                    name = "全角名前",
                                    summary = "日本語フルネームの例",
                                    value = "{\"name\": \"佐藤 一郎\"}"
                            ),
                    }
            )

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "正常にメッセージが返されました。",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HelloResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "バリデーションエラー",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/hello/{name}")
    public String helloWithPath(@PathVariable String name) {
        return helloService.getPersonalizedMessage(name);
    }

    @PostMapping("/hello")
    public HelloResponse postHello(@Valid @org.springframework.web.bind.annotation.RequestBody HelloRequest request) {
        String message = "Hello, " + request.getName() + "!";
        LocalDateTime now = LocalDateTime.now();
        return new HelloResponse(message, now);

    }
}
