package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(String nombre, String email ,String password, String role){
        User usuario = User.builder()
                .nombre(nombre)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(role != null ? role : "USER")
                .build();
        return userRepository.save(usuario);
    }

    public User register(String nombre, String email, String password){
        return register(nombre, email, password, "USER");
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
