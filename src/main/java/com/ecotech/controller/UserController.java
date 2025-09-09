package com.ecotech.controller;

import com.ecotech.model.User;
import com.ecotech.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado!");
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userRepository.findByEmail(user.getEmail())
                .filter(u -> u.getSenha().equals(user.getSenha()))
                .map(u -> ResponseEntity.ok("Login bem-sucedido"))
                .orElse(ResponseEntity.status(401).body("Credenciais inválidas"));
    }

    @GetMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
