package com.example.hello_springboot;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeTest {
    public static void main(String[] args){
        String encoded = new BCryptPasswordEncoder().encode("password");
        System.out.println(encoded);
    }
}
