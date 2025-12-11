package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.dto.AuthRequest;
import com.example.demo.security.dto.AuthResponse;
import com.example.demo.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername(), user.getRole(), null)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(null, null, null, "Usuario ya existe"));
        }

        // SIEMPRE crea USER
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername(), user.getRole(), null)
        );
    }

    // 🔥 ENDPOINT ESPECIAL PARA ADMIN
    @PostMapping("/register-admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody AuthRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(null, null, null, "Usuario ya existe"));
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ADMIN") // AQUI SI
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername(), user.getRole(), null)
        );
    }
}
