package com.example.hello_springboot.controller;

import com.example.hello_springboot.dto.AuthRequest;
import com.example.hello_springboot.dto.AuthResponse;
import com.example.hello_springboot.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    //    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("ログイン試行: " + authRequest.getUsername() + authRequest.getPassword());
//            System.out.println("authenticationManager: " + authenticationManager);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), authRequest.getPassword()
                    )
            );
            System.out.println("認証成功");

            String token = jwtUtil.generateToken(authRequest.getUsername());
            System.out.println("トークン生成成功");
            return new AuthResponse(token);

        } catch (AuthenticationException e) {
            System.out.println("認証失敗：" + e.getMessage());
            throw new RuntimeException("Invalid username/password");
        }
    }
}

