package com.example.hello_springboot.controller;

import com.example.hello_springboot.dto.AuthRequest;
import com.example.hello_springboot.dto.AuthResponse;
import com.example.hello_springboot.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Test
    void loginSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        UserDetailsService userDetailsService = Mockito.mock(UserDetailsService.class); // ← 追加！
        JwtUtil jwtUtil = new JwtUtil();

        UserDetails userDetails = new User("airi", "password1", Collections.emptyList());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(userDetailsService.loadUserByUsername("airi")).thenReturn(userDetails); // ← 重要！

        // AuthController のインスタンス生成（必要に応じて引数を増やす）
        AuthController controller = new AuthController(authenticationManager, userDetailsService, jwtUtil);

        AuthRequest request = new AuthRequest();
        request.setUsername("airi");
        request.setPassword("password1");

        // Act
        ResponseEntity<?> response = controller.login(request);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertInstanceOf(AuthResponse.class, response.getBody());

        AuthResponse authResponse = (AuthResponse) response.getBody();
        assertNotNull(authResponse.getToken());
        assertTrue(authResponse.getToken().length() > 10);
    }

    @Test
    void loginFail() {
        AuthRequest request = new AuthRequest();
        request.setUsername("wrong");
        request.setPassword("wrong");
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        JwtUtil jwtUtil = new JwtUtil();
        AuthController authController = new AuthController(authenticationManager, jwtUtil);
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Authentication failed"));

        assertThrows(RuntimeException.class, () -> {
            authController.login(request);
        });

//        // Arrange
//        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
//        JwtUtil jwtUtil = new JwtUtil();
//        AuthController controller = new AuthController(authenticationManager, jwtUtil);
//
//        AuthRequest request = new AuthRequest();
//        request.setUsername("wrong");
//        request.setPassword("wrong");
//
//        when(authenticationManager.authenticate(any()))
//                .thenThrow(new RuntimeException("Authentication failed"));
//
//        // Act
//        ResponseEntity<?> response = controller.login(request);
//
//        // Assert
//        assertEquals(401, response.getStatusCodeValue());
//        assertEquals("Invalid username or password", response.getBody());
    }
}
