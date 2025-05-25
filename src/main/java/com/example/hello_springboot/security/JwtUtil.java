package com.example.hello_springboot.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your-very-secret-key-should-be-long";
    private static final Key secretKey= Keys.hmacShaKeyFor("your-very-secret-key-should-be-long".getBytes());

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1時間
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<GrantedAuthority> extractAuthorities(String token){
        Claims claims = extractAllClaims(token);
        Object roles = claims.get("roles");

        if(roles instanceof Collection<?>){
            return ((Collection<?>) roles).stream()
                    .map(role -> {
                            if(role instanceof Map){
                                return new SimpleGrantedAuthority((String) ((Map<?,?>) role).get("authority"));
                            }
                            return new SimpleGrantedAuthority(role.toString());
                        })
                        .collect(Collectors.toList());
                    }
        return new ArrayList<>();
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
