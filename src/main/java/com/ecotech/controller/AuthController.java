package com.ecotech.controller;

import com.ecotech.config.JwtUtil;
import com.ecotech.dto.*;
import com.ecotech.model.User;
import com.ecotech.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        try {
            User u = userService.register(req.getNome(), req.getEmail(), req.getSenha(), req.getEndereco());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        var userOpt = userService.findByEmail(req.getEmail());
        if (userOpt.isEmpty() || !userService.checkPassword(userOpt.get(), req.getSenha())) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
        String token = jwtUtil.generateToken(userOpt.get().getEmail());
        return ResponseEntity.ok(new AuthResponse(token, userOpt.get().getNome(), userOpt.get().getEmail()));
    }
}
