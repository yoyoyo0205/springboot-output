package com.example.hello_springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //ログインしたユーザ名
        return "こんにちは、"+ username +"さん！";
    }

    @GetMapping("/common")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String common(){
        return "誰でもアクセス可能な共通API";
    }
}
