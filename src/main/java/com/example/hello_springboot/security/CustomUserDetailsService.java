package com.example.hello_springboot.security;

import com.example.hello_springboot.entity.UserEntity;
import com.example.hello_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException{

        System.out.println("DB検索: " + username);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("ユーザーが見つかりません"));
            System.out.println("パスワード" + user.getPassword() );
            System.out.println("DBユーザー取得成功: " + user.getUsername());
            System.out.println("照合確認: " + new BCryptPasswordEncoder().matches("password", user.getPassword()));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) //すでにBCrypt化されている前提
                .roles(user.getRole()) // 例: "USER"
                .build();
    }
}
