package com.example.hello_springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //ログインしたユーザ名

        // 現在時刻を取得
        LocalTime now = LocalTime.now();
        String greeting;

        // 時間帯による挨拶文言分岐
        if (now.isBefore(LocalTime.of(12,0))) {
            greeting = "おはようございます";
        }else if(now.isBefore(LocalTime.of(18,0))) {
            greeting = "こんにちは";
        }else {
            greeting = "こんばんは";
        }
        return greeting + "、" + username +"さん！";
    }

    @GetMapping("/common")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String common(){
        return "誰でもアクセス可能な共通API";
    }
}
